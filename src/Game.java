/**
 * Created by es on 9/22/17.
 */
public class Game {
    String manager;
    String ip;
    String port;
    String player1;
    String player1ip;
    String player1prot;
    String player2;
    String player2ip;
    String player2prot;
    String player3;
    String player3ip;
    String player3prot;
    String picture;

    public Game(String manager, String ip, String port) {
        this.manager = manager;
        this.ip = ip;
        this.port = port;
    }

    public void newPicture (){
        picture = "";
    }

    public void openPicture (String str){
        picture = str;
    }

    public void savePicture (){

    }

    public String[] getGameInfo(){
        String[] info = {ip,port};
        return info ;
    }

    public int addPlayer(String player, String ip, String port){
        if (player1 == null){
            this.player1 = player;
            this.player1ip = ip;
            this.player1prot = port;
            return 1;
        }else if(player2 == null){
            this.player2 = player;
            this.player2ip = ip;
            this.player2prot = port;
            return 2;
        }else if(player3 == null){
            this.player3 = player;
            this.player3ip = ip;
            this.player3prot = port;
            return 3;
        }
        return -1;
    }
}
