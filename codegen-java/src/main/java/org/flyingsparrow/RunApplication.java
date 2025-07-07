package org.flyingsparrow;

import org.flyingsparrow.bean.TableInfo;
import org.flyingsparrow.builder.BuildBase;
import org.flyingsparrow.builder.BuildPo;
import org.flyingsparrow.builder.BuildQuery;
import org.flyingsparrow.builder.BuildTable;

import java.util.List;

/**
 * 启动类
 * @author Siborne
 */
public class RunApplication {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList = BuildTable.getTables();

        BuildBase.execute();

        for (TableInfo tableInfo : tableInfoList){
            BuildPo.execute(tableInfo);

            BuildQuery.execute(tableInfo);
        }
    }
}
