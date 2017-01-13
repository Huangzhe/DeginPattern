package com.sh.lynn.hz.developbox.logframe;

import java.io.File;

/**
 * Created by Administrator on 2016/8/15.
 */
public interface ILog {
    /**消息日志
     * */
    void write(int level, String msg);

    /**文件日志
     * */
    void write(int level, File file);
}
