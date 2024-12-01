package com.astrolightz.pocketbox;

public class License
{
    private String name;
    private String details;
    private String license;
    private String url;

    /**
     * Constructor for License
     * @param name Who/What the license is for
     * @param details Details of the license (e.g. License Name)
     * @param license License text
     * @param url URL to the project
     */
    public License(String name, String details, String license, String url)
    {
        this.name = name;
        this.details = details;
        this.license = license;
        this.url = url;
    }

    // Getters
    public String getName()
    {
        return name;
    }

    public String getDetails()
    {
        return details;
    }

    public String getLicense()
    {
        return license;
    }

    public String getUrl()
    {
        return url;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public void setLicense(String license)
    {
        this.license = license;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
