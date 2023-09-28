package com.gpt.dumpgpt.shared;

import java.io.IOException;

public interface Serializable {
    /**
     * @return Returns a Serializer instance
     * containing serialized representation
     * of object
     */
    public Serializer serialize() throws DukeException, IOException;

    /**
     * @param serializer Serializer instances that object can use
     *                   to attempt to read its properties from
     */
    public void deserialize(Serializer serializer) throws DukeException, IOException;
}
