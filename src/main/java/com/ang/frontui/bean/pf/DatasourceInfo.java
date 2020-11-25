package com.ang.frontui.bean.pf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value="数据源信息",description="数据源信息(主键ID，创建时间为系统自动生成，不需要传入)")
public class DatasourceInfo {

    @ApiModelProperty(value = "主键ID")
    @Id
    private Long id;

    @ApiModelProperty(value = "数据源名称，自定义")
    private String dataName;

    @ApiModelProperty(value = "数据源类型1.vertica,2.oracle,3.mysql")
    private Integer datasourceType;

    @ApiModelProperty(value = "数据库名称，跟数据库一致")
    private String  datasourceName;

    @ApiModelProperty(value = "数据库所在机器ip")
    private String  machineIp;

    @ApiModelProperty(value = "数据库端口号")
    private Integer port;

    @ApiModelProperty(value = "连接用户名")
    private String connUser;

    @ApiModelProperty(value = "连接密码")
    private String connPwd;


    @ApiModelProperty(value = "连接url")
    private String connectUrl;

    @ApiModelProperty(value = "数据源描述")
    private String databaseDesc;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "报表状态（1启用0禁用）")
    private Integer status;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
