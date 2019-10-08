# scala-java-maven1
基于maven依赖，利用scala编写spark，打包jar

## wordvec的简单实现
使用哈工大分词器分词，maven依赖如下：

`<dependency>   
    <groupId>com.hankcs</groupId>   
    <artifactId>hanlp</artifactId>   
    <version>portable-1.2.8</version>   
</dependency>`

`<dependency>     
    <groupId>org.scalanlp</groupId>    
    <artifactId>nak_2.10</artifactId>     
    <version>1.3</version>    
</dependency>`    
  
处理流程：  
1、处理语料  
2、分词，全角转半角、停用词处理、分词、存储  
3、Skip-gram模型训练   
skip-gram是将词语作为输入，预测周围的上下文。   
CBOW是将上下文作为输入，预测词语本身。   

