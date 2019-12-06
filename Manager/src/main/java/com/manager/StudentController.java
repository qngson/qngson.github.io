/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manager;

import com.entity.Student;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Lapop1
 */
@ManagedBean(name = "studentController")
@ViewScoped
public class StudentController extends BaseController {

    private static final Logger logger = Logger.getLogger(StudentController.class);
    private Student student;
    private List<Student> students;
    private String studentCode;
    

    @PostConstruct
    public void init() {
        System.out.println("Start Init ProvinceController.......................................................");
        students = getStudentService().findAllstudent();
        System.out.println("students size " + students.size());
      
        reset();
        System.out.println("End Init ProvinceController.......................................................");

    }

    public void reset() {
        student = new Student();
        studentCode = "";
    }

    public void save() {
        System.out.println("Begin save, code = " + student.getCode() + ", name = " + student.getName() + ", birth= " + student.getBirth() + " ,province " + student.getProvince());
        FacesContext context = FacesContext.getCurrentInstance();
        boolean validData = true;
        if (student.getCode() == null || student.getCode().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã bắt buộc nhập", null));
            validData = false;
        } else {
            Student sCheck = getStudentService().findByCode(student.getCode());
            System.out.println("scheck :" + sCheck);
            if (sCheck != null) {
                if (student.getId() != null) {
                    System.out.println("student.getId() = " + student.getId() + ", sCheck.getId() = " + sCheck.getId());
                    if (!student.getId().equals(sCheck.getId()) || student.getId() == sCheck.getId()) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + student.getCode() + " đã có rồi", null));
                        validData = false;

                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mã " + student.getCode() + " đã có rồi", null));
                    validData = false;
                }
            }
        }
        if (student.getName() == null || student.getName().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tên bắt buộc nhập", null));
            validData = false;
        }
        if (student.getBirth() == null || student.getBirth().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ngày sinh bắt buộc nhập", null));
            validData = false;
        }
        if (student.getProvince() == null || student.getProvince().isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Quê quán bắt buộc nhập", null));
            validData = false;
        }
        if (validData) {
            getStudentService().save(student);
            students = getStudentService().findAllstudent();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Lưu thành công", null));
            reset();
        }

    }

    public void edit(Student stu) {
        student = stu;
       

    }

    public void delete(Student stu) {
        System.out.println("Delete");
        FacesContext context = FacesContext.getCurrentInstance();
        if(stu != null) {
            if(getStudentService().checkUsed(stu.getId())) {
                System.out.println(getStudentService().checkUsed(stu.getId()));
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dữ liệu đang sử dụng, không thể xóa", null));
            } else {
                getStudentService().deleteStudent(stu.getId());
                students = getStudentService().findAllstudent();
                reset();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Xóa dữ liệu thành công", null));
            }
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

  

}
