package Entity;

/**
 * @author zjf
 * @date 20190523   上午9点25分
 * 歌曲实体类
 */
public class SongEntity {
    private Tag tag;
    private String path;
    private String length;
    private String isLike;

    public String getIsLike() {
        return isLike;
    }
    public void setLike(String isLike) {
        if(isLike.equals("YES")){
            this.isLike = "YES";
        }
        else{
            this.isLike = "NO";
        }
    }
    public void setYesLike() {
        this.isLike = "YES";
    }
    public void setNoLike() {
        this.isLike = "NO";
    }
    public Tag getTag() {
        if(tag==null){
            tag=new Tag();
        }
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    @Override
    public String toString() {
        return "Song [tag=" + tag + ", path=" + path + ", length=" + length + ", isLike=" + isLike + "]";
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        SongEntity other=(SongEntity) obj;
        return this.path.equals(other.path);
    }
}
