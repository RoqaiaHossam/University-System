
import java.util.Stack;

public class University_1 {

    // ======================= Course Class =======================
    public class Course {
        private NewStudent head;
        private int courseID;
        private int numStudents;

        private class NewStudent {
            private EnrolledStudent student;
            private NewStudent next;

            NewStudent(EnrolledStudent student) {
                this.student = student;
                this.next = null;
            }
        }

        public class EnrolledStudent {
            private int studentID;

            public EnrolledStudent(int studentID) {
                this.studentID = studentID;
            }

            public int getID() {
                return studentID;
            }
        }

        public Course(int courseID) {
            this.courseID = courseID;
            this.head = null;
            this.numStudents = 0;
        }

        public int getID() {
            return courseID;
        }

        public int getNumberOfStudents() {
            return numStudents;
        }

        public boolean hasStudent(int studentID) {
            NewStudent current = head;
            while (current != null) {
                if (current.student.getID() == studentID) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        public void listStudentsInCourse() {
            if (head == null) {
                System.out.println("No students enrolled in this course.");
                return;
            }
            System.out.println("Students enrolled in Course ID " + courseID + ":");
            NewStudent current = head;
            while (current != null) {
                System.out.println("Student ID: " + current.student.getID());
                current = current.next;
            }
        }

        public boolean isFullCourse() {
            return numStudents >= 30;
        }
    }

    // ======================= Student Class =======================
    public class Student {
        private CourseNode head;
        private int courseCount;
        private int id;

        private class CourseNode {
            private Course course;
            private CourseNode next;

            CourseNode(Course course) {
                this.course = course;
                this.next = null;
            }
        }

        public Student(int id) {
            this.id = id;
            this.head = null;
            this.courseCount = 0;
        }

        public int getID() {
            return id;
        }

        public int getCourseCount() {
            return courseCount;
        }

        public boolean hasCourse(int courseID) {
            CourseNode current = head;
            while (current != null) {
                if (current.course.getID() == courseID) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        public void listCoursesForStudent() {
            if (head == null) {
                System.out.println("Student " + id + " is not enrolled in any courses.");
                return;
            }
            System.out.println("Courses for Student ID " + id + ":");
            CourseNode current = head;
            while (current != null) {
                System.out.println("Course ID: " + current.course.getID());
                current = current.next;
            }
        }

        
    }

    // ======================= AllCourses Class =======================
    public class AllCourses {
        private CourseNode head;
        private Course lastCourseAdded;

        private class CourseNode {
            private Course course;
            private CourseNode next;

            CourseNode(Course course) {
                this.course = course;
                this.next = null;
            }
        }

        public AllCourses() {
            head = null;
            lastCourseAdded = null;
        }

        public void addCourse(int courseID) {
            if (findCourse(courseID) != null) return;

            Course newCourse = new Course(courseID);
            CourseNode newNode = new CourseNode(newCourse);

            if (head == null) {
                head = newNode;
            } else {
                CourseNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }

            lastCourseAdded = newCourse;
        }

        public void removeCourse(int courseID) {
        University.Course courseToRemove = allCourses.findCourse(courseID);
        if (courseToRemove == null) {
            System.out.println("Course not found.");
        return;
    }

   
    University.Course.NewStudent currStudent = courseToRemove.head;
    while (currStudent != null) {
        int studentID = currStudent.student.getID();
        University.Student student = allStudents.findStudent(studentID);
        
        if (student.hasCourse(courseID)) {
            if (student.getCourseCount() > 2) {
             
                University.Student.CourseNode prev = null;
                University.Student.CourseNode curr = student.head;
                while (curr != null) {
                    if (curr.course.getID() == courseID) {
                        if (prev == null) {
                            student.head = curr.next;
                        } else {
                            prev.next = curr.next;
                        }
                        student.courseCount--;
                        break;
                    }
                    prev = curr;
                    curr = curr.next;
                }
            } else {
                System.out.println("Student " + student.getID() + " must remain enrolled in at least 2 courses. Course not removed from this student.");
            }
        }

        currStudent = currStudent.next;
    }
            if (head == null) {
                System.out.println("The list is empty.");
                return;
            }
            if (head.course.getID() == courseID) {
                head = head.next;
                System.out.println("Course removed successfully.");
                return;
            }

            CourseNode current = head;
            while (current.next != null && current.next.course.getID() != courseID) {
                current = current.next;
            }

            if (current.next != null) {
                current.next = current.next.next;
                System.out.println("Course removed successfully.");
            } else {
                System.out.println("Course not found.");
            }
        }

        public void listCoursesByID() {
            if (head == null) {
                System.out.println("No courses found.");
                return;
            }

            System.out.println("Courses List:");
            CourseNode current = head;
            while (current != null) {
                System.out.println("Course ID: " + current.course.getID());
                current = current.next;
            }
        }

        public Course findCourse(int id) {
            CourseNode current = head;
            while (current != null) {
                if (current.course.getID() == id) {
                    return current.course;
                }
                current = current.next;
            }
            return null;
        }

        public void sortCoursesByID() {
            if (head == null || head.next == null) {
                System.out.println("No courses to sort or only one course present.");
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                CourseNode current = head;

                while (current.next != null) {
                    if (current.course.getID() > current.next.course.getID()) {
                        Course temp = current.course;
                        current.course = current.next.course;
                        current.next.course = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);

            System.out.println("Courses sorted successfully.");
        }

        public void getLastCourseAdded() {
            System.out.println("Last course added. Course with Id "+lastCourseAdded.getID());
        }
    }

    // ======================= AllStudents Class =======================
    public class AllStudents {
        private StudentNode head;
        private Student lastStudentAdded;

        private class StudentNode {
            private Student student;
            private StudentNode next;

            StudentNode(Student student) {
                this.student = student;
                this.next = null;
            }
        }

        public AllStudents() {
            head = null;
            lastStudentAdded = null;
        }

        public void addStudent(int id) {
            Student newStudent = new Student(id);
            StudentNode newNode = new StudentNode(newStudent);

            if (head == null) {
                head = newNode;
            } else {
                StudentNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }

            lastStudentAdded = newStudent;
        }

        public void removeStudent(int id) {
            Student student= findStudent(id);
            if(student==null){
                System.out.println("Student not found.");
                return;
            
            }
            Student.CourseNode current=student.head;
            while(current!=null){
            int courseID=current.course.getID();
            removeEnrollment(id,courseID);
            current=current.next;
            
            
            
            }if (head.student.getID()==id){
            head=head.next;
            }
            else{
                 StudentNode prev=head;   
                    while(prev.next!=null&&prev.next.student.getID()!=id){
                        prev=prev.next;
                    
                    }
                    if (prev.next!=null){
                        prev.next=prev.next.next;
                    
                    }
                    
                    
                    }
            
            System.out.println("Student and allenrollment removed successfully.");
            
            
        }

        public void listStudentsByID() {
            if (head == null) {
                System.out.println("No students found.");
                return;
            }

            System.out.println("Students List:");
            StudentNode current = head;
            while (current != null) {
                System.out.println("Student ID: " + current.student.getID());
                current = current.next;
            }
        }

        public Student findStudent(int id) {
            StudentNode current = head;
            while (current != null) {
                if (current.student.getID() == id) {
                    return current.student;
                }
                current = current.next;
            }
            return null;
        }

        public void sortStudentsByID() {
            if (head == null || head.next == null) {
                System.out.println("No students to sort or only one student present.");
                return;
            }

            boolean swapped;
            do {
                swapped = false;
                StudentNode current = head;

                while (current.next != null) {
                    if (current.student.getID() > current.next.student.getID()) {
                        Student temp = current.student;
                        current.student = current.next.student;
                        current.next.student = temp;
                        swapped = true;
                    }
                    current = current.next;
                }
            } while (swapped);

            System.out.println("Students sorted successfully.");
        }

        public void getLastStudentAdded() {
            System.out.println("Last student add. Student with id "+lastStudentAdded.getID());
        }
    }

    // ======================= University Fields =======================
    

    private AllStudents allStudents;
    private AllCourses allCourses;
    private Stack<Action> undoStack; 
    private Stack<Action> redoStack; 


    private static class Action {
        String operation;
        int studentID;
        int courseID;

        public Action(String operation, int studentID, int courseID) {
            this.operation = operation;
            this.studentID = studentID;
            this.courseID = courseID;
        }
    }

    public University() {
        allStudents = new AllStudents();
        allCourses = new AllCourses();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public AllStudents getAllStudents() {
        return allStudents;
    }

    public AllCourses getAllCourses() {
        return allCourses;
    }

    public void enrollStudent(int studentID, int courseID) {
        Student student = allStudents.findStudent(studentID);
        Course course = allCourses.findCourse(courseID);

        if (student == null || course == null) {
            System.out.println("Student or Course not found.");
            return;
        }

        if (student.hasCourse(courseID)) {
            System.out.println("Student already enrolled in this course.");
            return;
        }

        if (student.getCourseCount() >= 7) {
            System.out.println("Student cannot register more than 7 courses.");
            return;
        }

        if (course.getNumberOfStudents() >= 30) {
            System.out.println("Course is full (maximum 30 students).");
            return;
        }

        Student.CourseNode newCourseNode = student.new CourseNode(course);
        newCourseNode.next = student.head;
        student.head = newCourseNode;
        student.courseCount++;

        Course.EnrolledStudent newEnrolledStudent = course.new EnrolledStudent(studentID);
        Course.NewStudent newStudentNode = course.new NewStudent(newEnrolledStudent);
        newStudentNode.next = course.head;
        course.head = newStudentNode;
        course.numStudents++;

        System.out.println("Student " + studentID + " enrolled in course " + courseID);

        undoStack.push(new Action("enroll", studentID, courseID));
        redoStack.clear();
    }

    public void removeEnrollment(int studentID, int courseID) {
        Student student = allStudents.findStudent(studentID);
        Course course = allCourses.findCourse(courseID);

        if (student == null || course == null) {
            System.out.println("Student or Course not found.");
            return;
        }
        if (!student.hasCourse(courseID)) {
            System.out.println("Student is not enrolled in this course.");
            return;
        }

        if (student.getCourseCount() <= 2) {
            System.out.println("Student must remain enrolled in at least 2 courses.");
            return;
        }

        Student.CourseNode prev = null;
        Student.CourseNode curr = student.head;
        boolean removedFromStudent = false;
        while (curr != null) {
            if (curr.course.getID() == courseID) {
                if (prev == null) {
                    student.head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                student.courseCount--;
                removedFromStudent = true;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        
        Course.NewStudent prevStudent = null;
        Course.NewStudent currStudent = course.head;
        boolean removedFromCourse = false;
        while (currStudent != null) {
            if (currStudent.student.getID() == studentID) {
                if (prevStudent == null) {
                    course.head = currStudent.next;
                } else {
                    prevStudent.next = currStudent.next;
                }
                course.numStudents--;
                removedFromCourse = true;
                break;
            }
            prevStudent = currStudent;
            currStudent = currStudent.next;
        }

        if (removedFromStudent && removedFromCourse) {
            System.out.println("Student " + studentID + " removed from course " + courseID);
            undoStack.push(new Action("remove", studentID, courseID));
            redoStack.clear();
        } else {
            System.out.println("can't remove student from course,please try again.");
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();

            if (action.operation.equals("enroll")) {
                Student student = allStudents.findStudent(action.studentID);
                Course course = allCourses.findCourse(action.courseID);
                if (student != null && course != null) {
                    Student.CourseNode prev = null;
                    Student.CourseNode curr = student.head;
                    boolean removedFromStudent = false;
                    while (curr != null) {
                        if (curr.course.getID() == action.courseID) {
                            if (prev == null) {
                                student.head = curr.next;
                            } else {
                                prev.next = curr.next;
                            }
                            student.courseCount--;
                            removedFromStudent = true;
                            break;
                        }
                        prev = curr;
                        curr = curr.next;
                    }

                    Course.NewStudent prevStudent = null;
                    Course.NewStudent currStudent = course.head;
                    boolean removedFromCourse = false;
                    while (currStudent != null) {
                        if (currStudent.student.getID() == action.studentID) {
                            if (prevStudent == null) {
                                course.head = currStudent.next;
                            } else {
                                prevStudent.next = currStudent.next;
                            }
                            course.numStudents--;
                            removedFromCourse = true;
                            break;
                        }
                        prevStudent = currStudent;
                        currStudent = currStudent.next;
                    }

                    if (removedFromStudent && removedFromCourse) {
                        System.out.println("Undo succeeded and Removed student " + action.studentID + " from course " + action.courseID);
                        redoStack.push(new Action("enroll", action.studentID, action.courseID));
                    } else {
                        System.out.println("Undo failed for enroll action,please try again.");
                        undoStack.push(action);
                    }
                }
            } else if (action.operation.equals("remove")) {
                Student student = allStudents.findStudent(action.studentID);
                Course course = allCourses.findCourse(action.courseID);
                if (student != null && course != null) {
                    Student.CourseNode newCourseNode = student.new CourseNode(course);
                    newCourseNode.next = student.head;
                    student.head = newCourseNode;
                    student.courseCount++;

                    Course.EnrolledStudent newEnrolledStudent = course.new EnrolledStudent(action.studentID);
                    Course.NewStudent newStudentNode = course.new NewStudent(newEnrolledStudent);
                    newStudentNode.next = course.head;
                    course.head = newStudentNode;
                    course.numStudents++;

                    System.out.println("Undo succeeded and Enrolled student " + action.studentID + " back into course " + action.courseID);
                    redoStack.push(new Action("remove", action.studentID, action.courseID)); 
                } else {
                    System.out.println("Undo failed for remove action,please try again.");
                    undoStack.push(action); 
                }
            }
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();

            if (action.operation.equals("enroll")) {
                Student student = allStudents.findStudent(action.studentID);
                Course course = allCourses.findCourse(action.courseID);
                if (student != null && course != null) {
                    Student.CourseNode newCourseNode = student.new CourseNode(course);
                    newCourseNode.next = student.head;
                    student.head = newCourseNode;
                    student.courseCount++;

                    Course.EnrolledStudent newEnrolledStudent = course.new EnrolledStudent(action.studentID);
                    Course.NewStudent newStudentNode = course.new NewStudent(newEnrolledStudent);
                    newStudentNode.next = course.head;
                    course.head = newStudentNode;
                    course.numStudents++;

                    System.out.println("Redo succeeded and Enrolled student " + action.studentID + " in course " + action.courseID);
                    undoStack.push(new Action("enroll", action.studentID, action.courseID)); 
                } else {
                    System.out.println("Redo failed for enroll action,please try again.");
                    redoStack.push(action); 
                }
            } else if (action.operation.equals("remove")) {
                Student student = allStudents.findStudent(action.studentID);
                Course course = allCourses.findCourse(action.courseID);
                if (student != null && course != null) {
                    Student.CourseNode prev = null;
                    Student.CourseNode curr = student.head;
                    boolean removedFromStudent = false;
                    while (curr != null) {
                        if (curr.course.getID() == action.courseID) {
                            if (prev == null) {
                                student.head = curr.next;
                            } else {
                                prev.next = curr.next;
                            }
                            student.courseCount--;
                            removedFromStudent = true;
                            break;
                        }
                        prev = curr;
                        curr = curr.next;
                    }
                    
                    Course.NewStudent prevStudent = null;
                    Course.NewStudent currStudent = course.head;
                    boolean removedFromCourse = false;
                    while (currStudent != null) {
                        if (currStudent.student.getID() == action.studentID) {
                            if (prevStudent == null) {
                                course.head = currStudent.next;
                            } else {
                                prevStudent.next = currStudent.next;
                            }
                            course.numStudents--;
                            removedFromCourse = true;
                            break;
                        }
                        prevStudent = currStudent;
                        currStudent = currStudent.next;
                    }

                    if (removedFromStudent && removedFromCourse) {
                        System.out.println("Redo succeeded and Removed student " + action.studentID + " from course " + action.courseID);
                        undoStack.push(new Action("remove", action.studentID, action.courseID));
                    } else {
                        System.out.println("Redo failed for remove action,please try again.");
                        redoStack.push(action); 
                    }
                }
            }
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public void listCoursesByStudent(int studentID) {
        Student student = allStudents.findStudent(studentID);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            student.listCoursesForStudent();
        }
    }

    public void listStudentsByCourse(int courseID) {
        Course course = allCourses.findCourse(courseID);
        if (course == null) {
            System.out.println("Course not found.");
        } else {
            course.listStudentsInCourse();
        }
    }
    public boolean isNormalStudent(int studentID) {
    Student student = allStudents.findStudent(studentID);
    if (student == null) {
        return false;
    }
    int numCourses = student.getCourseCount();
    if (numCourses >= 2 && numCourses <= 7) {
        System.out.println("true");
        return true;
       
    } else {
        System.out.println("false");
        return false;
    }
}
}
