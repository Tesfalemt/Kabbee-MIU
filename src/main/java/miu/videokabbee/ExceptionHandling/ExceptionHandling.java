package miu.videokabbee.ExceptionHandling;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor

public class ExceptionHandling {
    private String errorMessage;

    private List<String> errorMessagesFromValidity;
    public ExceptionHandling(List<String> errorMessagesFromValidity){
        this.errorMessagesFromValidity=errorMessagesFromValidity;
    }
    public ExceptionHandling(String message){
        this.errorMessage=message;
    }

    @Override
    public String toString() {

        if(errorMessage==null&&errorMessagesFromValidity!=null){
            return errorMessagesFromValidity.toString();
        }else if(errorMessage!=null&&errorMessagesFromValidity==null){
            return errorMessage;
        }

        return
                "errorMessage='" + errorMessage + '\n' +
                ", errorMessagesFromValidity=" + errorMessagesFromValidity  ;
    }


    public String getMessage() {
        return errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExceptionHandling that)) return false;
        return Objects.equals(errorMessage, that.errorMessage) && Objects.equals(errorMessagesFromValidity, that.errorMessagesFromValidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMessage, errorMessagesFromValidity);
    }
}
