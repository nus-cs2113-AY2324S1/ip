package Duchess.FunctionObjects;

import Duchess.ErrorObjects.DuchessError;

public class ErrorHandler {
    public ErrorHandler() {
    }

    public void HandleError(DuchessError error) {
        error.HandleError();
    }
}
