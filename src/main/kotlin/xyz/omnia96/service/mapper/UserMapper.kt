package xyz.omnia96.service.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xyz.omnia96.service.model.User

@Mapper
interface UserMapper {
    @Insert("INSERT INTO [omnia96].[dbo].[user] ([name], [account_id], [token], [gmt_create], [gmt_modified]) VALUES (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    fun  insert(user: User)

    @Select("SELECT [name],[account_id],[token],[gmt_create],[gmt_modified] FROM [omnia96].[dbo].[user] WHERE [token] = #{token}")
    fun findByToken(@Param("token") token: String?):User

    @Select("SELECT [name],[account_id],[token],[gmt_create],[gmt_modified] FROM [omnia96].[dbo].[user] WHERE [account_id] = #{accountId}")
    fun findUserByAccountId(user: User): User
}