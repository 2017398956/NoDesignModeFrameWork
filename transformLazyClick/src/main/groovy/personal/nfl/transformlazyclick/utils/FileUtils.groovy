package personal.nfl.transformlazyclick.utils

import org.aspectj.util.FileUtil

class FileUtils{
    static boolean copyFile(File from , File to){
        if(null == from || to == null){
            return false
        }else {
            FileUtil.copyFile(from , to)
            return true
        }
        return false
    }

    static boolean copyDirectory(File fromDir , File toDir){
        if(null == fromDir || toDir == null){
            return false
        }else {
            FileUtil.copyDir(fromDir , toDir)
            return true
        }
        return false
    }
}

