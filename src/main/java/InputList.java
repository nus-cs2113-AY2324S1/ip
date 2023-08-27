public class InputList {
    private static String[] allUserInputs = {}; //array of inputs

    public static void addToInputList(String input){
        String[] newInputList = new String[allUserInputs.length+1];
        System.arraycopy(allUserInputs, 0, newInputList, 0, allUserInputs.length);
        newInputList[allUserInputs.length] = input;
        allUserInputs = newInputList;
    }

    public static void printInputList(){
        for(int i = 0; i<allUserInputs.length; i++){
            System.out.printf("    %d: %s%n", i, allUserInputs[i]);
        }
    }
}
