package rednosed.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rednosed.app.dto.common.ResponseDto;
import rednosed.app.dto.request.UserNicknameDto;
import rednosed.app.dto.response.UserDuplicatedStatusDto;
import rednosed.app.security.oauth.info.PrincipalDetails;
import rednosed.app.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    //1-1. 사용자 정보 설정(닉네임 중복 확인)
    @PostMapping("/check-nickname")
    public ResponseDto<?> checkUserNickname(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid UserNicknameDto userNicknameDto
    ) {
        return ResponseDto.ok(userService.checkUserNickname(principalDetails.getUser(), userNicknameDto));
    }

    //1-2. 사용자 정보 설정(닉네임 설정)
    @PostMapping("/more-info")
    public ResponseDto<?> saveUserNickname(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid UserNicknameDto userNicknameDto
    ) {
        userService.saveUserNickname(principalDetails.getUser(), userNicknameDto);
        return ResponseDto.ok(null);
    }

    //2. 마이페이지(우표)
    @GetMapping("/stamp-info")
    public ResponseDto<?> showUserStampMyPage(
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        return ResponseDto.ok(userService.showUserStampMyPage(principalDetails.getUser()));
    }
}
