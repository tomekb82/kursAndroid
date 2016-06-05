package calcluator.android.tb.pl.calculator;

/**
 * Created by tomek on 05.06.16.
 */
public enum Operation {

    NONE(""), ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private final String key;

    private Operation(String key) {
        this.key = key;
    }
    public static Operation operationFromKey(String key){
        for (Operation operation : values()) {
            if(operation.key.equals(key)){
                return operation;
            }
        }
        return NONE;

    }
}
