package com.brandolff.msproductmanagement.util.ManageFiles;

import com.brandolff.msproductmanagement.util.ManageFiles.exception.CopyFileException;
import com.brandolff.msproductmanagement.util.ManageFiles.exception.FileTooLargeException;
import com.brandolff.msproductmanagement.util.ManageFiles.exception.MissingFileException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManageFilesImpl implements ManageFiles{


    private final String userFilesBasePath = "/temp/{userId}/profile";
    private final String PROFILE_DIR = "/profile";
    private final Long maxFileUploadSize = 2000000L;

    @Override
    public String saveFiles( MultipartFile file ) {
        String userId = "1";
        saveProfilePhoto(file, userId);
//        List<String> urls = new ArrayList<>();
//        urls.add("url1");
//        urls.add("url2");
//        urls.add("url3");
        String urls = "sadasdsdadasdasdsadasdasda";
        return urls;
    }

    public String getRootLocationForUserUpload(String userId) {

        StringBuilder builder = new StringBuilder();

        builder.append(userFilesBasePath);
        builder.append("/");
        builder.append(userId);

        String location = builder.toString();

        createDirectoryIfItDoesntExist(location);

        return location;
    }

    protected void createDirectoryIfItDoesntExist(String dir) {
        final Path path = Paths.get(dir);

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                System.out.println("Problem creating directory " + dir);
            }
        }
    }

    public String getRootLocationForUserProfileImageUpload(String userId) {

        String base = getRootLocationForUserUpload(userId);

        StringBuilder builder = new StringBuilder(base);
        builder.append("/");
        builder.append(PROFILE_DIR);

        String location = builder.toString();

        createDirectoryIfItDoesntExist(location);

        return location;
    }

    protected void validateFile(MultipartFile file, Long maxFileUploadSize)
            throws MissingFileException, FileTooLargeException {
        checkFileExistence(file);
        checkFileSize(file, maxFileUploadSize);
    }

    private void checkFileSize(MultipartFile file, Long maxFileUploadSize) throws FileTooLargeException {
        Long x = file.getSize();
        if (file.getSize() > maxFileUploadSize) {
            String message = "File is too large - max size is " + maxFileUploadSize;
            throw new FileTooLargeException(message);
        }
    }

    private void checkFileExistence(MultipartFile file) throws MissingFileException {
        if (file == null)
            throw new MissingFileException("No file sent!");
        if (StringUtils.isEmpty(file.getName()))
            throw new MissingFileException("No file sent!");
    }

    protected void deleteAllFilesInDirectory(Path rootLocation) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(rootLocation)) {
            directoryStream.forEach(path -> {
                path.toFile().delete();
            });
        } catch (IOException ie) {
            System.out.println("Problem trying to delete files in " + rootLocation.toString());
        }
    }

    private void saveFile(MultipartFile file, String userId, Path rootLocation) throws CopyFileException {
        String newFileName = getNewFileName(file, userId);

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, rootLocation.resolve(newFileName));
        } catch (IOException ie) {
            System.out.println("Problem uploading file!");
            throw new CopyFileException("Failed to upload!");
        }
    }

    private String getNewFileName( MultipartFile file, String userId ) {
        return "photo user" + userId + " " + file.getName();
    }

    public void saveProfilePhoto(MultipartFile file, String userId)
            throws MissingFileException, FileTooLargeException, CopyFileException {

        validateFile(file, maxFileUploadSize);
        Path rootLocation = Paths.get(getRootLocationForUserProfileImageUpload(userId));
        System.out.println("Root location is " + rootLocation);

        deleteAllFilesInDirectory(rootLocation);
        saveFile(file, userId, rootLocation);
    }

}
