package com.example.uploadexample.services;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.image.ImagingOpException;
import java.io.IOException;

public interface FileHandler {
    public String singleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes)
        throws ImagingOpException, IOException;
}
