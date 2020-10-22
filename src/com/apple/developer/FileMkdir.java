package com.apple.developer;

import java.io.File;

@SuppressWarnings("all")

public class FileMkdir {
    public static String Mkdirs(String fileAddress,String fileName){
        String message="";
        File address=new File(fileAddress);
        File fileExits = new File(fileAddress+File.separator+fileName);
        if(!address.exists()){
            message="存放文件的地址不存在或非法！";
        }else if(fileExits.exists()){
            message="已存在同名文件，请删除或更改新建文件名！";
        }else{
            for (int i=0+1;i<35+1;i++){
                File file = new File(fileAddress+File.separator+fileName+File.separator+String.valueOf(i));
                file.mkdirs();
            }
            boolean exit=true;
            for(int j=1;j<=35;j++){
                File files=new File(fileAddress+File.separator+fileName+File.separator+String.valueOf(j));
                if(!files.exists()){
                    exit=false;
                    break;
                }
            }
            if(exit==true){
                message="文件创建成功！";
            }
        }
        return message;
    }
}
