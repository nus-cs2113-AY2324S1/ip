package com.gpt.dumpgpt.shared;

import java.io.ByteArrayInputStream;
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
     * meant for writing / serialization
     */
    public Serializer() {
        totalFields = 0;
        outputStream = new ByteArrayOutputStream();
    }

    /**
     * Instantiates Serializer object
     * meant for reading / deserialization
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

    /**
     * Writes string using UTF-8 encoding into Serializer instance for serialization.
     *
     * @param str string value to be serialized
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     */
    public void putString(String str) throws DukeException {
        putString(str, StandardCharsets.UTF_8);
    }

    /**
     * Writes string with specified character set into Serializer
     * instance for serialization.
     *
     * @param str     string value to be serialized
     * @param charset character set encoding to be used
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     */
    public void putString(String str, Charset charset) throws DukeException {
        checkCanSerialize();
        byte[] strBytes = str.getBytes(charset);
        outputStream.write(STR_MARKER);
        outputStream.writeBytes(intToBytes(strBytes.length));
        outputStream.writeBytes(str.getBytes(charset));
        ++totalFields;
    }

    /**
     * Writes boolean into Serializer instance for serialization.
     *
     * @param value boolean to be serialized
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     */
    public void putBoolean(boolean value) throws DukeException {
        checkCanSerialize();
        outputStream.write(BOOL_MARKER);
        outputStream.write(value ? 1 : 0);
        ++totalFields;
    }

    /**
     * Writes integer into Serializer instance for serialization.
     *
     * @param value integer to be serialized
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     */
    public void putInt(int value) throws DukeException {
        checkCanSerialize();
        outputStream.write(INT_MARKER);
        outputStream.writeBytes(intToBytes(value));
        ++totalFields;
    }

    /**
     * Writes byte array into Serializer instance for serialization.
     *
     * @param bytes byte array containing bytes to be serialized
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     */
    public void putBytes(byte[] bytes) throws DukeException {
        checkCanSerialize();
        outputStream.write(BYTES_MARKER);
        outputStream.writeBytes(intToBytes(bytes.length));
        outputStream.writeBytes(bytes);
        ++totalFields;
    }

    /**
     * Writes byte array into Serializer instance for serialization.
     *
     * @param serializable An object that implements {@link Serializable} interface
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     * @throws IOException   when {@code serializable} fails to serialize or call to
     *                       {@code serializer.writeObject()} fails.
     */
    public void putSerializable(Serializable serializable) throws DukeException, IOException {
        ByteArrayOutputStream serializableBytes = new ByteArrayOutputStream();
        Serializer serializer = serializable.serialize();
        serializer.writeObject(serializableBytes);
        putBytes(serializableBytes.toByteArray());
        ++totalFields;
    }

    /**
     * Write serialized instance to {@code stream}
     *
     * @param stream stream to write serialized object to
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object type was not set
     * @throws IOException   when write to {@code stream} fails
     */
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

    /**
     * Reads object info from {@link #inputStream}
     *
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
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

    /**
     * Reads a string from {@link #inputStream} and decodes using UTF-8
     *
     * @return string that was read and decoded using UTF-8 or
     * {@code null} if next field in stream is not a string
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
    public String readString() throws DukeException, IOException {
        return readString(StandardCharsets.UTF_8);
    }

    /**
     * Reads a string from {@link #inputStream} and decodes using specified {@code charset}
     *
     * @param charset Character set to decode string value with
     * @return string that was read and decoded with specified {@code charset}
     * or {@code null} if next field in stream is not a string
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
    public String readString(Charset charset) throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();

        byte[] strValue = readMarkedByteField(STR_MARKER);
        if (strValue == null) {
            return null;
        }

        return new String(strValue, charset);
    }

    /**
     * Reads a boolean from {@link #inputStream}
     *
     * @return boolean read or {@code null} if next field in stream is not a boolean
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
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

    /**
     * Reads an integer from {@link #inputStream}
     *
     * @return integer read or {@code null} if next field in stream is not an integer
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
    public Integer readInt() throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();

        if (!verifyMarkerByte(INT_MARKER)) {
            return null;
        }


        --totalFields;
        return readRawInt();
    }

    /**
     * Reads bytes from {@link #inputStream}
     *
     * @return bytes read or {@code null} if next field in stream is not bytes
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized
     * @throws IOException   when read from {@link #inputStream fails}
     */
    public byte[] readBytes() throws DukeException, IOException {
        checkCanDeserialize();
        checkHasFieldsLeft();
        return readMarkedByteField('R');
    }

    /**
     * Reads bytes from {@link #inputStream} and returns a Serializer instance
     *
     * @return {@link Serializable} object read
     * @throws DukeException when Serializer instance
     *                       was not instantiated for serialization
     *                       or object read yet or if no more fields
     *                       are left in the object currently being
     *                       deserialized.
     *                       <p>
     *                       Also thrown when call to {@code serializer.readObjectInfo()}
     *                       fails on deserialized object
     * @throws IOException   when read from {@link #inputStream fails}
     */
    public Serializer readSerializable() throws DukeException, IOException {
        byte[] objectBytes = readBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(objectBytes);
        Serializer serializer = new Serializer(inputStream);
        serializer.readObjectInfo();
        return serializer;
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

    /**
     * Assert function use to enforce that correctness
     * of {@link Serializer} instance's type
     *
     * @param type expected type
     * @throws DukeException when current instance type is not
     *                       equal to expected type
     */
    public void assertType(String type) throws DukeException {
        if (!getType().equals(type)) {
            throw new DukeException("Unexpected type...");
        }
    }
}