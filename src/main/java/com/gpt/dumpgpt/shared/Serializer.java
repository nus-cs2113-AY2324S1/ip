package com.gpt.dumpgpt.shared;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Serializer {
    private final static char OBJ_MARKER = 'O';
    private final static char STR_MARKER = 'S';
    private final static char BOOL_MARKER = 'B';
    private final static char INT_MARKER = 'I';
    private final static char BYTES_MARKER = 'R';

    private String objectType = null;
    private int totalFields;
    private ByteArrayOutputStream outputStream = null;
    private InputStream inputStream = null;

    /**
     * Instantiates Serializer object
     * meant for writing
     */
    public Serializer() {
        totalFields = 0;
        outputStream = new ByteArrayOutputStream();
    }

    /**
     * Instantiates Serializer object
     * meant for reading
     *
     * @param inputStream stream to read from
     */
    public Serializer(InputStream inputStream) {
        totalFields = -1;
        this.inputStream = inputStream;
    }

    private byte[] intToBytes(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    private int bytesToInt(byte[] value) {
        return ByteBuffer.wrap(value).getInt();
    }

    public void setType(String objectType) {
        this.objectType = objectType;
    }

    public void putString(String str) throws DukeException {
        putString(str, StandardCharsets.UTF_8);
    }

    public void putString(String str, Charset charset) throws DukeException {
        checkCanSerialize();
        byte[] strBytes = str.getBytes(charset);
        outputStream.write(STR_MARKER);
        outputStream.writeBytes(intToBytes(strBytes.length));
        outputStream.writeBytes(str.getBytes(charset));
        ++totalFields;
    }

    public void putBoolean(boolean value) throws DukeException {
        checkCanSerialize();
        outputStream.write(BOOL_MARKER);
        outputStream.write(value ? 1 : 0);
        ++totalFields;
    }

    public void putInt(int value) throws DukeException {
        checkCanSerialize();
        outputStream.write(INT_MARKER);
        outputStream.writeBytes(intToBytes(value));
        ++totalFields;
    }

    public void putBytes(byte[] bytes) throws DukeException {
        checkCanSerialize();
        outputStream.write(BYTES_MARKER);
        outputStream.writeBytes(intToBytes(bytes.length));
        outputStream.writeBytes(bytes);
        ++totalFields;
    }

    public void writeObject(OutputStream stream) throws DukeException, IOException {
        checkCanSerialize();
        byte[] objectTypeBytes = objectType.getBytes();
        stream.write(OBJ_MARKER);
        stream.write(intToBytes(objectTypeBytes.length));
        stream.write(objectTypeBytes);
        stream.write(intToBytes(totalFields));
        outputStream.writeTo(stream);
    }

    private void checkCanSerialize() throws DukeException {
        if (outputStream == null) {
            throw new DukeException("Serializer instance not serializable");
        }

        if (objectType == null) {
            throw new DukeException("Serialization of unknown object...");
        }
    }

    private Character readMarkerByte() throws IOException {
        int markerByte = inputStream.read();
        if (markerByte == -1) {
            return null;
        }
        return (char) markerByte;
    }

    private boolean verifyMarkerByte(char marker) throws IOException {
        inputStream.mark(0);
        Character markerByte = readMarkerByte();
        if (markerByte == null || markerByte != marker) {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }
            return false;
        }
        return true;
    }

    private byte[] readByteField() throws IOException {
        int length = readRawInt();
        --totalFields;
        return inputStream.readNBytes(length);
    }

    private byte[] readMarkedByteField(char marker) throws IOException {
        if (!verifyMarkerByte(marker)) {
            return null;
        }
        return readByteField();
    }

    private int readRawInt() throws IOException {
        byte[] intBytes = inputStream.readNBytes(4);
        return bytesToInt(intBytes);
    }

    public void readObjectInfo() throws DukeException, IOException {
        checkCanDeserialize();

        if (totalFields > 0) {
            throw new DukeException("Current object still contain unread fields...");
        }

        byte[] objectTypeBytes = readMarkedByteField(OBJ_MARKER);
        if (objectTypeBytes == null) {
            throw new DukeException("Failed to read object info...");
        }
        setType(new String(objectTypeBytes));

        byte[] fieldCount = inputStream.readNBytes(4);
        totalFields = bytesToInt(fieldCount);
    }

    public String readString() throws DukeException, IOException {
        return readString(StandardCharsets.UTF_8);
    }

    public String readString(Charset charset) throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();

        byte[] strValue = readMarkedByteField(STR_MARKER);
        if (strValue == null) {
            return null;
        }

        return new String(strValue, charset);
    }

    public Boolean readBoolean() throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();

        if (!verifyMarkerByte(BOOL_MARKER)) {
            return null;
        }

        int readByte = inputStream.read();
        if (readByte == -1) {
            return null;
        }

        --totalFields;
        return readByte == 1;
    }

    public Integer readInt() throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();

        if (!verifyMarkerByte(INT_MARKER)) {
            return null;
        }


        --totalFields;
        return readRawInt();
    }

    public byte[] readBytes() throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();
        return readMarkedByteField('R');
    }

    private void checkCanDeserialize() throws DukeException {
        if (inputStream == null) {
            throw new DukeException("Serializer instance not deserializable");
        }
    }

    private void checkHasFieldsLeft() throws DukeException {
        if (totalFields == -1) {
            throw new DukeException("Object info not read...");
        }

        if (totalFields == 0) {
            throw new DukeException("No more object fields left...");
        }
    }

    public String getType() {
        return objectType;
    }

}