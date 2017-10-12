/**
 * Created by es on 9/22/17.
 */
public class Game {
    String manager;
    String ip;
    String port;
    String player1;
    String player1ip;
    String player1port;
    String player2;
    String player2ip;
    String player2port;
    String player3;
    String player3ip;
    String player3port;
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
            this.player1port = port;
            return 1;
        }else if(player2 == null){
            this.player2 = player;
            this.player2ip = ip;
            this.player2port = port;
            return 2;
        }else if(player3 == null){
            this.player3 = player;
            this.player3ip = ip;
            this.player3port = port;
            return 3;
        }
        return -1;
    }

    public boolean checkGame(String ip, String port){
        boolean result = this.ip.equals(ip) && this.port.equals(port);
        return result;
    }

    public boolean checkEmptySit(){
        return player1==null||player2==null||player3==null;
    }

    public String[] getUser(){
        String[] user = {manager,player1,player2,player3};
        return user;
    }
    public boolean checkUser(String user){
        return player1==user||player2==user||player3==user;
    }
}
