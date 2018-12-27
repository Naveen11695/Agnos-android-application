package naveen.agnosbeta.agnos;

import android.graphics.Bitmap;

/**
 * Created by naVeen on 14-01-2018.
 */

public class getter_setter {
    private static String first_name,last_name,age,contact_no,email,com_name,office_contact_no,office_email,office_add,csi_member_s,service_type,description;
    private static String user_profile_link;


    public static String getFirst_name() {
        return first_name;
    }

    public static void setFirst_name(String first_name) {
        getter_setter.first_name = first_name;
    }

    public static String getLast_name() {
        return last_name;
    }

    public static void setLast_name(String last_name) {
        getter_setter.last_name = last_name;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        getter_setter.age = age;
    }

    public static String getContact_no() {
        return contact_no;
    }

    public static void setContact_no(String contact_no) {
        getter_setter.contact_no = contact_no;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        getter_setter.email = email;
    }

    public static String getCom_name() {
        return com_name;
    }

    public static void setCom_name(String com_name) {
        getter_setter.com_name = com_name;
    }

    public static String getOffice_contact_no() {
        return office_contact_no;
    }

    public static void setOffice_contact_no(String office_contact_no) {
        getter_setter.office_contact_no = office_contact_no;
    }

    public static String getOffice_email() {
        return office_email;
    }

    public static void setOffice_email(String office_email) {
        getter_setter.office_email = office_email;
    }

    public static String getOffice_add() {
        return office_add;
    }

    public static void setOffice_add(String office_add) {
        getter_setter.office_add = office_add;
    }


    public static String getCsi_member_s() {
        return csi_member_s;
    }

    public static void setCsi_member_s(String csi_member_s) {
        getter_setter.csi_member_s = csi_member_s;
    }

    public static String getService_type() {
        return service_type;
    }

    public static void setService_type(String service_type) {
        getter_setter.service_type = service_type;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        getter_setter.description = description;
    }
    public static void setuser_profile_link(String user_profile_link) {
        getter_setter.user_profile_link = user_profile_link;
    }
    public static String getuser_profile_link() {
        return user_profile_link;
    }
}
