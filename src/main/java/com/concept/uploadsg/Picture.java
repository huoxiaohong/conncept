package com.concept.uploadsg;

/**
 * @author <a href="mailto:zlz.3907@gmail.com">Zhong Lizhi</a>
 */
public class Picture extends UploadFile {

    private boolean isCover;

    private String dir;

    private int[][] ranges = null;// new int[][]{{160, 120}};

    private boolean isUniformScale;

    /**
     *
     */
    private String thumbnailUrl;

    private String middleThumbnaiUrl;

    private String largeThumbnaiUrl;

    /**
     * Get the <code>dir</code> value.
     * 
     * @return the <code>dir</code> value of the <code>String</code>.
     */
    public final String getDir() {
        return dir;
    }

    /**
     * Set the <code>dir</code> value.
     * 
     * @param dir the dir to set.
     */
    public final void setDir(String dir) {
        this.dir = dir;
    }

    /**
     * Get the <code>isCover</code> value.
     * 
     * @return the <code>isCover</code> value of the <code>boolean</code>.
     */
    public final boolean isCover() {
        return isCover;
    }

    /**
     * Set the <code>isCover</code> value.
     * 
     * @param isCover the isCover to set.
     */
    public final void setCover(boolean isCover) {
        this.isCover = isCover;
    }

    /**
     * Get the <code>middleThumbnaiUrl</code> value.
     * 
     * @return the <code>middleThumbnaiUrl</code> value of the <code>String</code> .
     */
    public final String getMiddleThumbnaiUrl() {
        return middleThumbnaiUrl;
    }

    /**
     * Set the <code>middleThumbnaiUrl</code> value.
     * 
     * @param middleThumbnaiUrl the middleThumbnaiUrl to set.
     */
    public final void setMiddleThumbnaiUrl(String middleThumbnaiUrl) {
        this.middleThumbnaiUrl = middleThumbnaiUrl;
    }

    /**
     * Get the <code>largeThumbnaiUrl</code> value.
     * 
     * @return the <code>largeThumbnaiUrl</code> value of the <code>String</code>.
     */
    public final String getLargeThumbnaiUrl() {
        return largeThumbnaiUrl;
    }

    /**
     * Set the <code>largeThumbnaiUrl</code> value.
     * 
     * @param largeThumbnaiUrl the largeThumbnaiUrl to set.
     */
    public final void setLargeThumbnaiUrl(String largeThumbnaiUrl) {
        this.largeThumbnaiUrl = largeThumbnaiUrl;
    }

    /**
     * Get the <code>thumbnailUrl</code> value.
     * 
     * @return the <code>thumbnailUrl</code> value of the <code>String</code>.
     */
    public final String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Set the <code>thumbnailUrl</code> value.
     * 
     * @param thumbnailUrl the thumbnailUrl to set.
     */
    public final void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public final void setRanges(int[][] ranges) {
        this.ranges = ranges;
    }

    public final int[][] getRanges() {
        return this.ranges;
    }

    /**
     * @return the isUniformScale
     */
    public boolean isUniformScale() {
        return isUniformScale;
    }

    /**
     * @param isUniformScale the isUniformScale to set
     */
    public void setUniformScale(boolean isUniformScale) {
        this.isUniformScale = isUniformScale;
    }

}
