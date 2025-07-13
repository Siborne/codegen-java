package org.flyingsparrow.builder;

import com.sun.org.apache.bcel.internal.Const;
import org.flyingsparrow.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildBase {
    private static Logger logger = LoggerFactory.getLogger(BuildBase.class);

    public static void execute() {
        List<String> headerInfoList = new ArrayList<>();

        //生成date枚举
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "DateTimePatternEnum", Constants.PATH_ENUMS);

        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_UTILS);
        build(headerInfoList, "DateUtils", Constants.PATH_UTILS);

        //生成BaseMapper
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_MAPPERS);
        build(headerInfoList, "BaseMapper", Constants.PATH_MAPPERS);

        //生成pageSize枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "PageSize", Constants.PATH_ENUMS);

        //生成SimplePage
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY);

        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".PageSize");
        build(headerInfoList, "SimplePage", Constants.PATH_QUERY);

        //生成BaseQuery
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_QUERY);
        build(headerInfoList, "BaseQuery", Constants.PATH_QUERY);

        //生成PaginationResultVO
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_VO);
        build(headerInfoList, "PaginationResultVO", Constants.PATH_VO);

        //生成Exception
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_EXCEPTION);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".ResponseCodeEnum");
        build(headerInfoList, "BusinessException", Constants.PATH_EXCEPTION);

        //生成ResponseCodeEnum枚举
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headerInfoList, "ResponseCodeEnum", Constants.PATH_ENUMS);

        //生成BaseController
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_CONTROLLER);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".ResponseCodeEnum");
        headerInfoList.add("import " + Constants.PACKAGE_VO + ".ResponseVO");
        build(headerInfoList, "ABaseController", Constants.PATH_CONTROLLER);

        //生成ResponseVO
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_VO);
        build(headerInfoList, "ResponseVO", Constants.PATH_VO);

        //生成AGlobalExceptionHandlerController
        headerInfoList.clear();
        headerInfoList.add("package " + Constants.PACKAGE_CONTROLLER);
        headerInfoList.add("import " + Constants.PACKAGE_ENUMS + ".ResponseCodeEnum");
        headerInfoList.add("import " + Constants.PACKAGE_VO + ".ResponseVO");
        headerInfoList.add("import " + Constants.PACKAGE_EXCEPTION + ".BusinessException");
        build(headerInfoList, "AGlobalExceptionHandlerController", Constants.PATH_CONTROLLER);
    }

    private static void build(List<String> headerInfoList, String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File file = new File(outPutPath, fileName + ".java");

        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;

        InputStream in = null;
        InputStreamReader inr = null;
        BufferedReader br = null;
        try {
            out = new FileOutputStream(file);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            String templatePath = BuildBase.class.getClassLoader().getResource("template/" + fileName + ".txt").getPath();
            in = new FileInputStream(templatePath);
            inr = new InputStreamReader(in, "utf-8");
            br = new BufferedReader(inr);

            for (String head : headerInfoList) {
                bw.write(head + ";");
                bw.newLine();
                bw.newLine();
            }


            String lineInfo = null;
            while ((lineInfo = br.readLine()) != null) {
                bw.write(lineInfo);
                bw.newLine();
            }

            bw.flush();

        } catch (Exception e) {
            logger.error("生成基础类:{},失败", fileName, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inr != null) {
                try {
                    inr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outw != null) {
                try {
                    outw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
