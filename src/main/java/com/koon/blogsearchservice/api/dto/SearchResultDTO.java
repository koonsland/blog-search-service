package com.koon.blogsearchservice.api.dto;

import com.koon.blogsearchservice.api.dto.kakao.BlogDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * 네이버 RESP
 * {
 *   "lastBuildDate": "Sat, 18 Mar 2023 11:51:44 +0900",
 *   "total": 13415,
 *   "start": 1,
 *   "display": 10,
 *   "items": [
 *     {
 *       "title": "심플한 2023년 11월 <b>굿노트<\/b> 먼슬리양식 PDF 공유",
 *       "link": "https:\/\/blog.naver.com\/ovo--vnv\/223042220616",
 *       "description": "속지 굿노트 2023 다이어리 무료 굿노트 다이어리 굿노트 스터디플래너 <b>굿노트 속지 무료<\/b> 굿노트... 다이어리 속지 2023 갤럭시탭 다이어리 속지 갤럭시 탭 서식 태블릿 속지 삼성노트 템플릿 다운... ",
 *       "bloggername": "A short but full life❣️",
 *       "bloggerlink": "blog.naver.com\/ovo--vnv",
 *       "postdate": "20230318"
 *     },
 *     ...
 *   ]
 * }
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDTO {
    private List<BlogDocument> documents;
    private int page;
    private int size;
    private int totalCount;
}
