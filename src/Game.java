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

    public String[] getUser(){
        String[] user = {manager,player1,player2,player3};
        return user;
    }
//    public String[][] getUser(){
//        String[][] user = new String[4][3];
//        user[0][0] = manager;
//        user[0][1] = ip;
//        user[0][2] = port;
//        user[1][0] = player1;
//        user[1][1] = player1ip;
//        user[1][2] = player1port;
//        user[2][0] = player2;
//        user[2][1] = player2ip;
//        user[2][2] = player2port;
//        user[3][0] = player3;
//        user[3][1] = player3ip;
//        user[3][2] = player3port;
//        return user;
//    }
}
