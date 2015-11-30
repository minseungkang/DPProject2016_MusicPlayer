import java.util.ArrayList;
import java.io.*;

public class MusicFile {
   private String composer, singer, name, genre, nation,
                  album, informationFileName, musicFileId;
   private String duration;
   private int playCount;
   
   private String fileName, fileAddress, fileInformationAddress;
   
   
   private Lyric lyrics;
   private String lyricsFileAddress, lyricsFileName;
   private ArrayList<String> recentPlay;
   
   private final String musicInformationDelimiter = "/";
   private final int musicDataNum = 12;

   MusicFile(){
      
   };
   MusicFile(String musicFileId){
      this.musicFileId = musicFileId;
   }
   MusicFile(String musicFileId, String fileInformationAddress){
      this.fileInformationAddress = fileInformationAddress;
      this.musicFileId = musicFileId;
      
      String[] information = getMusicFileInformation();

      setMusicInformation(information);
   }
   MusicFile(String musicFileId, String[] information, String musicFileName, String musicFileAddress){
      this.musicFileId = musicFileId;
      this.fileAddress = musicFileAddress;
      this.fileName = musicFileName;
      setMusicInformation(information);
   }
   
   
   public String[] getMusicFileInformation(){
      FileIO fileReader = new FileIO();
      
      String informationFileName = musicFileId;
      String[] tempInformation = fileReader.readTextFile(fileInformationAddress, informationFileName);
      String[] information = tempInformation[0].split("/");
      return information;
   }
   public void setMusicFileInformation(){
      FileIO fileReader = new FileIO();
      String[] writeInformation = {this.composer, this.singer, this.name, this.album, this.fileAddress, this.nation,
            this.duration, this.genre, Integer.toString(this.playCount), this.musicFileId,
            this.lyricsFileName, this.lyricsFileAddress};
      
      if(this.informationFileName == null){
         this.informationFileName = this.name + "_" + this.singer;
      }
      
      fileReader.writeTextFile(this.fileInformationAddress, this.informationFileName, writeInformation, musicInformationDelimiter);
   }

   
   
   public void setMusicInformation(String[] information){
   
      for(int i=0;i<musicDataNum;i++){
         if(information[i] == null){
            information[i] = "null";
         }
      }
      this.composer = information[0];
      this.singer = information[1];
      this.name = information[2];
      this.album = information[3];
      this.fileAddress = information[4];
      this.nation = information[5];
      this.duration = information[6];
      this.genre = information[7];
      try{
         this.playCount = Integer.parseInt(information[8]);
      }catch(Exception e){
         this.playCount = 0;
         information[8] = "0";
      }
      this.musicFileId = information[9];
      this.lyricsFileName = information[10];
      this.lyricsFileAddress = information[11];
      setMusicFileInformation();
   }
   
   
   
   // ~~~~~~~~~~~~~~~ 최근 재생된 기록에 관련된 함수
   public void addRecentPlay(String information){
      recentPlay.add(information);
   }
   public void deleteRecentPlay(int index){
      recentPlay.remove(index);
   }
   public void deleteRecentPlay(String information){
      recentPlay.remove(information);
   }
   public void deleteAllRecentPlay(){
      recentPlay.clear();
   }
   
   
   
   // ~~~~~~~~~~~~~~~~ Getter & Setter for private value
   public String getMusicFileId(){
      return musicFileId;
   }
   public String getcomposer() {
      return composer;
   }
   public void setcomposer(String composer) {
      this.composer = composer;
   }
   public String getSinger() {
      return singer;
   }
   public void setSinger(String singer) {
      this.singer = singer;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getFileName() {
      return fileName;
   }
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   public String getPlay_time() {
      return album;
   }
   public void setPlay_time(String play_time) {
      this.album = play_time;
   }
   public String getDuration() {
      return duration;
   }
   public void setDuration(String duration) {
      this.duration = duration;
   }
   public int getPlay_count() {
      return playCount;
   }
   public void setPlay_count(int play_count) {
      this.playCount = play_count;
   }
   public String getFile_address() {
      return fileAddress;
   }
   public void setFile_address(String file_address) {
      this.fileAddress = file_address;
   }
   public void setNation(String nation){
      this.nation = nation;
   }
   public String getNation(){
      return nation;
   }
   public Lyric getLyrics() {
      return lyrics;
   }
   public String getLyricsFileAddress(){
      return lyricsFileAddress;
   }
   public String getLyricsFileName(){
      return lyricsFileName;
   }
   public void setLyrics(Lyric lyrics, String lyricsFileName, String lyricsFileAddress) {
      this.lyrics = lyrics;
      this.lyricsFileName = lyricsFileName;
      this.lyricsFileAddress = lyricsFileAddress;
   }
   public ArrayList<String> getRecent_play() {
      return recentPlay;
   }
   public void setRecent_play(ArrayList<String> recent_play) {
      this.recentPlay = recent_play;
   }

}