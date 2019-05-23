package Entity;

import java.util.List;


/** 
* @author ZTF  
* @date 2017年3月18日 上午12:29:44 
* @version 1.0 
* @Description:   歌单的实体类
*/
public class SongMenu {
	private String songMenuName;
	private List<SongEntity> songList;
	private String createDate;
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSongMenuName() {
		return songMenuName;
	}
	public void setSongMenuName(String songMenuName) {
		this.songMenuName = songMenuName;
	}
	public List<SongEntity> getSongList() {
		return songList;
	}
	public void setSongList(List<SongEntity> songList) {
		this.songList = songList;
	}
	
}
 