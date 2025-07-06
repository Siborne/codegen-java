package org.flyingsparrow;

import org.flyingsparrow.bean.TableInfo;
import org.flyingsparrow.builder.BuildPo;
import org.flyingsparrow.builder.BuildTable;

import java.util.List;

/**
 * 启动类
 * @author Siborne
 */
public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList = BuildTable.getTables();
        for (TableInfo tableInfo : tableInfoList){
            BuildPo.execute(tableInfo);
        }
    }
}
