package com.ww.July.fifteen.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.ww.utils.UUIDUtil;



/**
 * @author: Sun
 * @create: 2019-07-15 15:46
 * @version: v1.0
 */
public class Main {

    private static String choiceQuestionDir = "/data1/kkclass_data/choice_question/";

    public static void main(String[] args) {
        // writeStringToFile(choiceQuestionDir + UUIDUtil.getUUID());
        // readStringByFile(choiceQuestionDir + UUIDUtil.getUUID());
        delFile(choiceQuestionDir + UUIDUtil.getUUID() + "207dab97d53344caa555d7eab4f69991");
    }

    /**
     * 使用hutool FileWriter写文件
     * @param filePath
     */
    public static void writeStringToFile(String filePath) {
        String choiceQuestionContent = "{\"title\":\"下面那个选项能是左边代码绘制的图形\",\"titleRemotePic\":\"https://static.kkcode.com/course_question_pic/b9c55007ae004378bd03be63ee7de4b8.png\",\"fileName\":\"b9c55007ae004378bd03be63ee7de4b8.png\",\"selectContent\":[{\"content\":\"\",\"remotePicUrl\":\"https://static.kkcode.com/course_question_pic/f5a701d4b1fe4af8acca3e3582d6e833.png\",\"fileName\":\"f5a701d4b1fe4af8acca3e3582d6e833.png\"},{\"content\":\"\",\"remotePicUrl\":\"https://static.kkcode.com/course_question_pic/85fc0e7e43c74620bbde2052afc03ab8.png\",\"fileName\":\"85fc0e7e43c74620bbde2052afc03ab8.png\"},{\"content\":\"\",\"remotePicUrl\":\"https://static.kkcode.com/course_question_pic/757f5b6518174b02923c7d2d0c2fe05d.png\",\"fileName\":\"757f5b6518174b02923c7d2d0c2fe05d.png\"},{\"content\":\"\",\"remotePicUrl\":\"https://static.kkcode.com/course_question_pic/efea1220119e47ad9e3a4719f8c5587d.png\",\"fileName\":\"efea1220119e47ad9e3a4719f8c5587d.png\"}],\"matchContent\":[{\"content\":\"\",\"remotePicUrl\":\"\",\"fileName\":\"\"},{\"content\":\"\",\"remotePicUrl\":\"\",\"fileName\":\"\"},{\"content\":\"\",\"remotePicUrl\":\"\",\"fileName\":\"\"},{\"content\":\"\",\"remotePicUrl\":\"\",\"fileName\":\"\"}],\"selectAnswer\":[2],\"selectAnalysis\":\"fedsagafdgfdsgsffgfds\"}";
        System.out.println(filePath);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(choiceQuestionContent);
    }

    /**
     * 使用hutool FileReader读取文件内容
     * @param filePath
     */
    public static void readStringByFile(String filePath) {
        FileReader fileReader = new FileReader(filePath);
        String readChoiceQuestionContent = fileReader.readString();
        System.out.println(readChoiceQuestionContent);
    }

    /**
     * 使用hutool FileUtil删除文件
     * @param filePath
     */
    public static void delFile(String filePath) {
        FileUtil.del(filePath);
    }
}
