package com.sh.lynn.hz.developbox.utils;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;

/**
 * Created by Administrator on 2016/7/4.
 */
public class TranMd5FileNameGenerator extends Md5FileNameGenerator {

    @Override
    public String generate(String imageUri) {
        if (imageUri.startsWith("http://")){
            if (imageUri.indexOf('?') == -1){//普通url 无需校验
                return super.generate(imageUri);
            }

            int pos = imageUri.indexOf('?');
            String head = imageUri.substring(0, pos);
            return super.generate(head);
        }
        return super.generate(imageUri);
    }
}
