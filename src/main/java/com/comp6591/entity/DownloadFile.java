package com.comp6591.entity;

import lombok.*;

import java.io.File;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DownloadFile {

        private String fileName;
        private File file;


}
