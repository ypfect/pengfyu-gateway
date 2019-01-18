package com.pengfyu.zuul.dto;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`sys_role_permission`")
public class SysRolePermission implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_permission.id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_permission.role_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    private Integer roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role_permission.per_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    private Integer perId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role_permission
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_permission.id
     *
     * @return the value of sys_role_permission.id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_permission.id
     *
     * @param id the value for sys_role_permission.id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_permission.role_id
     *
     * @return the value of sys_role_permission.role_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_permission.role_id
     *
     * @param roleId the value for sys_role_permission.role_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role_permission.per_id
     *
     * @return the value of sys_role_permission.per_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public Integer getPerId() {
        return perId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role_permission.per_id
     *
     * @param perId the value for sys_role_permission.per_id
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_permission
     *
     * @mbg.generated Fri Jan 18 21:27:39 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", perId=").append(perId);
        sb.append("]");
        return sb.toString();
    }
}