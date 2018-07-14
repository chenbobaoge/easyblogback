package com.bobochen.easyblogback.allinone.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class TxtController {

    @RequestMapping(value = "/readtxt" ,method = RequestMethod.POST)
    public boolean ReadTxt(){
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File moban = new File(path.getAbsolutePath(), "myreadexcel/read.log");

            FileInputStream fileInputStream = new FileInputStream(moban);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "gbk");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer sb = new StringBuffer();
            String text = null;
            int i=0;
            while((text = bufferedReader.readLine()) != null){
               System.out.println("第"+i+"行 "+text);
               i++;
            }
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }


    @RequestMapping(value = "/writetxt" ,method = RequestMethod.POST)
    public boolean WriteTxt(){
        FileWriter fw =null;
        BufferedWriter bw=null;
        try{
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File moban = new File(path.getAbsolutePath(), "myreadexcel/write.log");
            if(!moban.exists()){
                moban.createNewFile();
            }
            //第二个参数true是追加，false是覆盖
             fw = new FileWriter(moban,true);
             bw = new BufferedWriter(fw);
            for (int i=0;i<100;i++){
                bw.write("第"+i+"行 发的发的发顺丰\r\n");
            }

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            try {
                bw.close();
                fw.close();
            }catch (Exception ee)
            {

            }
        }

    }
}
