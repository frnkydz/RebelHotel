package edu.unlv.cs.rebelhotel.web;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.unlv.cs.rebelhotel.file.FileUpload;
import edu.unlv.cs.rebelhotel.file.StudentService;

@Controller
@RequestMapping("/file")
public class FileUploadController {
	private StudentService studentService;

	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@RequestMapping(params = "upload", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERUSER')")
	public String uploadForm(Model model) {
		return "file/upload";
	}

	@RequestMapping(params = "upload", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERUSER')")
	public String uploadFormHandler(@RequestParam("file") MultipartFile multipart_file, Model model) throws IOException {
		if (multipart_file.isEmpty()) {
			return "file/upload";
		}
		
		File file = File.createTempFile("students",".csv");
		multipart_file.transferTo(file);
		FileUpload fileUpload = new FileUpload(file);
		studentService.upload(fileUpload);

		//Object history; // whatever i get back from query for all fileuploads finder on fileuploads
		//model.addAttribute("fileUpload", history);
		return "file/show";
	}
}