package com.concept.uploadsg;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageHandler extends FileHandler {

    public boolean create(Picture picture, InputStream input) {
        if (null == input) {
            return false;
        }

        boolean ret = false;

        final String IMAGE_SCOPE = "/zbeans/";

        FileOutputStream out1 = null;
        try {
            BufferedImage img = null;
            try {
                img = ImageIO.read(input);
                if (null == img)
                    return false;

                // FIXME: 保存原始文件
                // dba.create ...
                // super.create(picture);
            } catch (IOException e) {
                e.printStackTrace();
                // com.ztools.exceptions.ExceptionPrinter.printStackTrace(e);
                return ret;
            }

            if (-1 == img.getWidth(null)) {
                return ret;
            }
            int[][] ranges = null != picture.getRanges() ? picture.getRanges() : new int[][] {};

            int[][] whRanges = new int[ranges.length + 1][2];
            whRanges[0] = new int[] { img.getWidth(null), img.getHeight(null) };
            for (int r = 1; r < whRanges.length; r++) {
                whRanges[r][0] = ranges[r - 1][0];
                whRanges[r][1] = ranges[r - 1][1];
            }

            String[] subfixes = new String[] { "", "_l", "_m", "_s" };
            final String S = "s";

            ret = true;
            for (int i = 0; i < whRanges.length; i++) {
                int maxWidth = whRanges[i][0];
                int maxHeight = whRanges[i][1];
                int width = maxWidth;
                int height = maxHeight;
                if (picture.isUniformScale()) {
                    double widthRate = ((double) img.getWidth(null)) / (double) maxWidth;
                    double heightRate = ((double) img.getHeight(null)) / (double) maxHeight;
                    double rate = Math.max(widthRate, heightRate);
                    width = (int) (((double) img.getWidth(null)) / rate);
                    height = (int) (((double) img.getHeight(null)) / rate);
                    // System.out.println("rate/w/h " + rate + "/" + width + "/" + height
                    // + "/" + widthRate + "/" + heightRate + "/" + maxWidth + "/"
                    // + maxHeight);
                }

                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(img.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0,
                        null);
                FileOutputStream out = null;
                try {

                    // String dir = dbCfg.getImageBase() + IMAGE_SCOPE + picture.getDir();

                    // String subfix = i < subfixes.length ? subfixes[i] : "_" + i;
                    // out = new FileOutputStream(dir + picture.getId() + subfix + "."
                    // + picture.getType());
                    // out = new FileOutputStream(picture.getAbsolutePath());
                    // FIXME: dba.create ...
                    // ret = ImageIO.write(tag, picture.getType(), out);

                } catch (Exception e) {
                    e.printStackTrace();
                    ret = false;
                } finally {
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return ret;

        } finally {
            if (null != out1) {
                try {
                    out1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
