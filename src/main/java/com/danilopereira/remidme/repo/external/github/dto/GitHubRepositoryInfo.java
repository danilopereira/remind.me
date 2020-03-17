package com.danilopereira.remidme.repo.external.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubRepositoryInfo {

    private float id;

    @JsonProperty("node_id")
    private String nodeId;

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private boolean privateFlag;

    private Owner owner;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String description;

    private boolean fork;

    private String url;

    @JsonProperty("forks_url")
    private String forksUrl;

    @JsonProperty("keys_url")
    private String keysUrl;

    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;

    @JsonProperty("teams_url")
    private String teamsUrl;

    @JsonProperty("hooks_url")
    private String hooksUrl;

    @JsonProperty("issue_events_url")
    private String issueEventsUrl;

    @JsonProperty("events_url")
    private String eventsUrl;

    @JsonProperty("assignees_url")
    private String assigneesUrl;

    @JsonProperty("branches_url")
    private String branchesUrl;

    @JsonProperty("")
    private String tagsUrl;

    @JsonProperty("blobs_url")
    private String blobsUrl;

    @JsonProperty("git_tags_url")
    private String gitTagsUrl;

    @JsonProperty("git_refs_url")
    private String gitRefsUrl;

    @JsonProperty("trees_url")
    private String treesUrl;

    @JsonProperty("statuses_url")
    private String statusesUrl;

    @JsonProperty("languages_url")
    private String languagesUrl;

    @JsonProperty("stargazers_url")
    private String stargazersUrl;

    @JsonProperty("contributors_url")
    private String contributorsUrl;

    @JsonProperty("subscribers_url")
    private String subscribersUrl;

    @JsonProperty("subscription_url")
    private String subscriptionUrl;

    @JsonProperty("commits_url")
    private String commitsUrl;

    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;

    @JsonProperty("comments_url")
    private String commentsUrl;

    @JsonProperty("issue_comment_url")
    private String issueCommentUrl;

    @JsonProperty("contents_url")
    private String contentsUrl;

    @JsonProperty("compare_url")
    private String compareUrl;

    @JsonProperty("merges_url")
    private String mergesUrl;

    @JsonProperty("archive_url")
    private String archiveUrl;

    @JsonProperty("downloads_url")
    private String downloadsUrl;

    @JsonProperty("issues_url")
    private String issuesUrl;

    @JsonProperty("pulls_url")
    private String pullsUrl;

    @JsonProperty("notifications_url")
    private String milestonesUrl;

    @JsonProperty("")
    private String notificationsUrl;

    @JsonProperty("labels_url")
    private String labelsUrl;

    @JsonProperty("releases_url")
    private String releasesUrl;

    @JsonProperty("deployments_url")
    private String deploymentsUrl;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("pushed_at")
    private String pushedAt;

    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("ssh_url")
    private String sshUrl;

    @JsonProperty("clone_url")
    private String cloneUrl;

    @JsonProperty("svn_url")
    private String svnUrl;

    private String homepage = null;

    private float size;

    @JsonProperty("stargazers_count")
    private float stargazersCount;

    @JsonProperty("watchers_count")
    private float watchersCount;

    private String language;

    @JsonProperty("has_issues")
    private boolean hasIssues;

    @JsonProperty("has_projects")
    private boolean hasProjects;

    @JsonProperty("has_downloads")
    private boolean hasDownloads;

    @JsonProperty("has_wiki")
    private boolean hasWiki;

    @JsonProperty("has_pages")
    private boolean hasPages;

    @JsonProperty("forks_count")
    private float forksCount;

    @JsonProperty("mirror_url")
    private String mirrorUrl = null;

    private boolean archived;

    private boolean disabled;

    @JsonProperty("open_issues_count")
    private float openIssuesCount;

    private float forks;

    @JsonProperty("open_issues")
    private float openIssues;

    private float watchers;

    @JsonProperty("default_branch")
    private String defaultBranch;
}