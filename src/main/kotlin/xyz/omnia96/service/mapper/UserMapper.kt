package xyz.omnia96.service.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import xyz.omnia96.service.model.User

@Mapper
interface UserMapper {
    @Insert("INSERT INTO [omnia96].[dbo].[user] ([name], [account_id], [token], [gmt_create], [gmt_modified]) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
     fun  insert(user: User)
}