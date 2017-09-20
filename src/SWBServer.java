import org.json.*;
import java.util.*;
/**
 * Created by ES on 2017/9/14.
 */
public class SWBServer {
    public static void main(String[] args){
        System.out.println("**Server Start**");

        try{
            
        }catch (Exception e){
            e.printStackTrace();
        }

        HashMap<String,String> map = new HashMap<String,String>();
        map.put("operation","chawWindow");
        JSONObject message = new JSONObject(map);
        String a = message.toString();
        JSONObject received_Message = new JSONObject(a);
        System.out.print(received_Message.get("operation"));
    }
}
