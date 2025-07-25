package org.flyingsparrow.builder;

import org.apache.commons.lang3.ArrayUtils;
import org.flyingsparrow.bean.Constants;
import org.flyingsparrow.bean.FieldInfo;
import org.flyingsparrow.bean.TableInfo;
import org.flyingsparrow.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class BuildQuery {

    private static final Logger logger = LoggerFactory.getLogger(BuildQuery.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_QUERY);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_BEAN_QUERY;

        File queryFile = new File(folder, className + ".java");

        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(queryFile);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            //构建query头部信息
            buildQueryHeader(tableInfo, bw);

            //构建query内容
            buildQueryBody(tableInfo, bw, className);

            bw.flush();
        } catch (Exception e) {
            logger.error("创建query失败", e);
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

    private static void buildQueryBody(TableInfo tableInfo, BufferedWriter bw, String className) throws IOException {
        //构建类注释
        BuildComment.createClassComment(bw, tableInfo.getComment() + "查询对象");
        bw.write("public class " + className + " extends BaseQuery {");
        bw.newLine();

        //构建属性
        buildJavaProperty(tableInfo, bw);
        //构建getter和setter
        buildGetSet(bw, tableInfo.getFieldList());
        buildGetSet(bw, tableInfo.getExtendFieldList());

        bw.write("}");
    }

    private static void buildQueryHeader(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("package " + Constants.PACKAGE_QUERY + ";");
        bw.newLine();
        bw.newLine();

        if (tableInfo.isHaveBigDecimal()) {
            bw.write("import java.math.BigDecimal;");
            bw.newLine();
        }
        if (tableInfo.isHaveDate() || tableInfo.isHaveDateTime()) {
            bw.write("import java.util.Date;");
            bw.newLine();
        }
        bw.newLine();
    }

    private static void buildJavaProperty(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        for (FieldInfo field : tableInfo.getFieldList()) {
            BuildComment.createFieldComment(bw, field.getComment());
            bw.write("\tprivate " + field.getJavaType() + " " + field.getPropertyName() + ";");
            bw.newLine();
            bw.newLine();

            //String类型的参数
            if (ArrayUtils.contains(Constants.SQL_STRING_TYPE, field.getSqlType())) {
                String propertyName = field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_FUZZY;
                bw.write("\tprivate " + field.getJavaType() + " " + propertyName + ";");
                bw.newLine();
                bw.newLine();

            }

            if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES, field.getSqlType()) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, field.getSqlType())) {
                bw.write("\tprivate String " + field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_START + ";");
                bw.newLine();
                bw.newLine();

                bw.write("\tprivate String " + field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_END + ";");
                bw.newLine();
                bw.newLine();
            }
        }
    }

    private static void buildGetSet(BufferedWriter bw, List<FieldInfo> fieldInfoList) throws IOException {
        for (FieldInfo field : fieldInfoList) {
            String tempField = StringUtils.upperCaseFirstLetter(field.getPropertyName());
            bw.write("\tpublic void set" + tempField + "(" + field.getJavaType() + " " + field.getPropertyName() + ") {");
            bw.newLine();
            bw.write("\t\tthis." + field.getPropertyName() + " = " + field.getPropertyName() + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            bw.write("\tpublic " + field.getJavaType() + " get" + tempField + "() {");
            bw.newLine();
            bw.write("\t\treturn this." + field.getPropertyName() + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
        }
    }
}
