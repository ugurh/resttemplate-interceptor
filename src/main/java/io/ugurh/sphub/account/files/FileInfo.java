package io.ugurh.sphub.account.files;

/**
 * @author harun ugur
 * @project resttemplate-interceptor
 * @created 5.05.2023 - 01:17
 */
public class FileInfo {
    private String name;
    private String url;

    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
