package org.flyingsparrow.builder;

import org.flyingsparrow.bean.Constants;
import org.flyingsparrow.bean.FieldInfo;
import org.flyingsparrow.bean.TableInfo;
import org.flyingsparrow.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapper {

    private static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPERS);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_MAPPERS;
        File mapperFile = new File(folder, className + ".java");

        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(mapperFile);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            // 构建Mapper头部信息(package/import)
            buildMapperHeader(bw);

            // 构建Mapper类
            buildMapperBody(tableInfo, bw, className);

            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.error("创建mappers失败", e);
        } finally {
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

    private static void buildMapperBody(TableInfo tableInfo, BufferedWriter bw, String className) throws IOException {
        //构建类注释
        BuildComment.createClassComment(bw, tableInfo.getComment() + "Mapper");
        bw.write("public interface " + className + "<T, P> extends BaseMapper {");
        bw.newLine();

        Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();

        for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMap.entrySet()) {
            List<FieldInfo> keyFieldInfoList = entry.getValue();

            Integer index = 0;
            StringBuilder methodName = new StringBuilder();

            StringBuilder methodParams = new StringBuilder();

            for (FieldInfo fieldInfo : keyFieldInfoList) {
                index++;
                methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                if (index < keyFieldInfoList.size()) {
                    methodName.append("And");
                }

                methodParams.append("@Param(\"" + fieldInfo.getPropertyName() + "\") " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName());
                if (index < keyFieldInfoList.size()) {
                    methodParams.append(", ");
                }

            }
            buildSelectBy(bw, methodName, methodParams);

            buildUpdateBy(bw, methodName, methodParams);

            buildDeleteBy(bw, methodName, methodParams);
        }
    }

    private static void buildDeleteBy(BufferedWriter bw, StringBuilder methodName, StringBuilder methodParams) throws IOException {
        BuildComment.createFieldComment(bw, "根据" + methodName + "删除");
        bw.write("\tLong deleteBy" + methodName + "(" + methodParams + ");");
        bw.newLine();
        bw.newLine();
    }

    private static void buildUpdateBy(BufferedWriter bw, StringBuilder methodName, StringBuilder methodParams) throws IOException {
        BuildComment.createFieldComment(bw, "根据" + methodName + "更新");
        bw.write("\tLong updateBy" + methodName + "(@Param(\"bean\") T t, " + methodParams + ");");
        bw.newLine();
        bw.newLine();
    }

    private static void buildSelectBy(BufferedWriter bw, StringBuilder methodName, StringBuilder methodParams) throws IOException {
        BuildComment.createFieldComment(bw, "根据" + methodName + "查询");
        bw.write("\tT selectBy" + methodName + "(" + methodParams + ");");
        bw.newLine();
        bw.newLine();
    }

    private static void buildMapperHeader(BufferedWriter bw) throws IOException {
        bw.write("package " + Constants.PACKAGE_MAPPERS + ";");
        bw.newLine();
        bw.newLine();

        bw.write("import org.apache.ibatis.annotations.Param;");
        bw.newLine();
        bw.newLine();
    }

}
