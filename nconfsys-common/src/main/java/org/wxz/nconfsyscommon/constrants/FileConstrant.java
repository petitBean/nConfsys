package org.wxz.nconfsyscommon.constrants;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/23 18:13
 */
public interface FileConstrant {

   public static final List<String> IMAG_TYPES=Arrays.asList(".jpg",".png",".jpeg",".gif");

   public static final List<String> DOC_ITYPES=Arrays.asList(".doc",".txt",".wps",".docx",".pdf");

   public static final List<String> ZIP_ITYPES=Arrays.asList(".rar",".zip");

   public static final List<String> EXCL_ITYPES=Arrays.asList(".xls",".xlsx");

   static final String BASE_LOCATION="F:/IdeaWorkspace/proj/UserResource";

   public static final String IMAG_LOCATION="/img";

   public static final String DOC_LOCATION="/doc";

   public static final String ZIP_LOCATION="/zip";

   public static final String EXCL_LOCATION="/excl";



}
