package com.danilopereira.remidme.user.utils;

import com.danilopereira.remidme.repo.dto.RepositoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public final class UserUtils {

    private UserUtils(){

    }

    public static String extractUserName(String githubUrl){
        final int beginIndex = githubUrl.lastIndexOf("/");
        return githubUrl.substring(beginIndex);
    }

    public static Page<RepositoryDTO> generatePageableResource(Pageable pageable, List<RepositoryDTO> repositoriesByUser) {
        final int start = (int) pageable.getOffset() - 1;
        final int end = (Math.min((start + pageable.getPageSize()), repositoriesByUser.size()));

        return new PageImpl<>(repositoriesByUser.subList(start, end), pageable, repositoriesByUser.size());
    }
}
