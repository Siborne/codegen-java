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

public class BuildController {
    private static final Logger logger = LoggerFactory.getLogger(BuildController.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_CONTROLLER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + "Controller";
        File controllerFile = new File(folder, className + ".java");

        OutputStream out = null;
        OutputStreamWriter outw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(controllerFile);
            outw = new OutputStreamWriter(out, "utf-8");
            bw = new BufferedWriter(outw);

            bw.write("package " + Constants.PACKAGE_CONTROLLER + ";");
            bw.newLine();
            bw.newLine();

            String serviceName = tableInfo.getBeanName() + "Service";

            String serviceBeanName = StringUtils.lowerCaseFirstLetter(serviceName);

            bw.write("import " + Constants.PACKAGE_PO + "." + tableInfo.getBeanName() + ";");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_QUERY + "." + tableInfo.getBeanQueryName() + ";");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_VO + ".ResponseVO;");
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_SERVICE + "." + serviceName + ";");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RequestBody;");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RequestMapping;");
            bw.newLine();
            bw.write("import org.springframework.web.bind.annotation.RestController;");
            bw.newLine();
            bw.newLine();

            bw.write("import javax.annotation.Resource;");
            bw.newLine();
            bw.write("import java.util.List;");
            bw.newLine();
            bw.newLine();


            BuildComment.createClassComment(bw, tableInfo.getComment() + "Controller");
            bw.write("@RestController");
            bw.newLine();
            bw.write("@RequestMapping(\"/" + StringUtils.lowerCaseFirstLetter(tableInfo.getBeanName()) + "\")");
            bw.newLine();
            bw.write("public class " + className + " extends ABaseController {");
            bw.newLine();
            bw.newLine();

            bw.write("\t@Resource");
            bw.newLine();

            bw.write("\tprivate " + serviceName + " " + serviceBeanName + ";");
            bw.newLine();
            bw.newLine();

            bw.write("\t@RequestMapping(\"loadDataList\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO loadDataList(" + tableInfo.getBeanQueryName() + " query) {");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(" + serviceBeanName + ".findListByPage(query));");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            BuildComment.createMethodComment(bw, "新增");
            bw.write("\t@RequestMapping(\"add\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO add(@RequestBody " + tableInfo.getBeanName() + " bean) {");
            bw.newLine();
            bw.write("\t\tthis." + serviceBeanName + ".add(bean);");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(null);");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            BuildComment.createMethodComment(bw, "批量新增");
            bw.write("\t@RequestMapping(\"addBatch\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO addBatch(@RequestBody List<" + tableInfo.getBeanName() + "> listBean) {");
            bw.newLine();
            bw.write("\t\tthis." + serviceBeanName + ".addBatch(listBean);");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(null);");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            BuildComment.createMethodComment(bw, "批量新增或修改");
            bw.write("\t@RequestMapping(\"addOrUpdateBatch\")");
            bw.newLine();
            bw.write("\tpublic ResponseVO addOrUpdateBatch(@RequestBody List<" + tableInfo.getBeanName() + "> listBean) {");
            bw.newLine();
            bw.write("\t\tthis." + serviceBeanName + ".addOrUpdateBatch(listBean);");
            bw.newLine();
            bw.write("\t\treturn getSuccessResponseVO(null);");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();

            for (Map.Entry<String, List<FieldInfo>> entry : tableInfo.getKeyIndexMap().entrySet()) {
                List<FieldInfo> keyFieldInfoList = entry.getValue();

                Integer index = 0;
                StringBuilder methodName = new StringBuilder();

                StringBuilder methodParams = new StringBuilder();

                StringBuilder parmasBuilder = new StringBuilder();
                for (FieldInfo fieldInfo : keyFieldInfoList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if (index < keyFieldInfoList.size()) {
                        methodName.append("And");
                    }

                    methodParams.append(fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName());

                    parmasBuilder.append(fieldInfo.getPropertyName());
                    if (index < keyFieldInfoList.size()) {
                        methodParams.append(", ");
                        parmasBuilder.append(", ");
                    }

                }
                BuildComment.createFieldComment(bw, "根据" + methodName + "查询");
                String requestName = "get" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + requestName + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO " + requestName + "(" + methodParams +
                        ") {");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(this." + serviceBeanName + ".get" + tableInfo.getBeanName() + "By" + methodName + "(" + parmasBuilder + "));");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();

                BuildComment.createFieldComment(bw, "根据" + methodName + "更新");
                requestName = "update" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + requestName + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO  " + requestName + "(" + tableInfo.getBeanName() + " bean, " + methodParams +
                        ") {");
                bw.newLine();
                bw.write("\t\tthis." + serviceBeanName + ".update" + tableInfo.getBeanName() + "By" + methodName + "(bean, " + parmasBuilder + ");");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(null);");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();

                BuildComment.createFieldComment(bw, "根据" + methodName + "删除");
                requestName = "delete" + tableInfo.getBeanName() + "By" + methodName;
                bw.write("\t@RequestMapping(\"" + requestName + "\")");
                bw.newLine();
                bw.write("\tpublic ResponseVO " + requestName + "(" + methodParams + ") {");
                bw.newLine();
                bw.write("\t\tthis." + serviceBeanName + ".delete" + tableInfo.getBeanName() + "By" + methodName + "(" + parmasBuilder +
                        ");");
                bw.newLine();
                bw.write("\t\treturn getSuccessResponseVO(null);");
                bw.newLine();
                bw.write("\t}");
                bw.newLine();
                bw.newLine();
            }

            bw.write("}");
            bw.newLine();

            bw.newLine();
            bw.flush();
        } catch (Exception e) {
            logger.error("创建service失败", e);
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
}
