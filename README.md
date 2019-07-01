##部署linux步骤
- 打包部署包

        使用maven进行打包，Lifecycle > install 

- 解压
        
        jar -xvf zjzc-manage-api.zip
        
- 转码

    启动脚本需要进行Windows转linux 使用dos2unix
    dos2unix是将Windows格式文件转换为Unix、Linux格式的实用命令。Windows格式文件的换行符为\r\n ,而Unix&Linux文件的换行符为\n. dos2unix命令其实就是将文件中的\r\n 转换为\n。
    而unix2dos则是和dos2unix互为孪生的一个命令，它是将Linux&Unix格式文件转换为Windows格式文件的命令
        yum -y install dos2unix*
        在config文件夹下执行：dos2unix *
        在prod 下执行：dos2unix *

- 检查启动文件
        
        vi start.sh 查看日志文件存放地址
- 运行
        
        sh start.sh