package org.mysite.mysitebackend.Service;

import com.aliyuncs.auth.sts.AssumeRoleResponse;
import org.mysite.mysitebackend.entity.Result;

import java.util.Map;

public interface OssService {
    Result<AssumeRoleResponse.Credentials> generateCredentials(Integer userId);

    Result<String> getBucket();

    Result<String> getRegion();

    Result<String> getEndPoint();
}
