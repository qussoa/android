package com.biz.memoapp.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
/*
    FTS Ver 4
    Full Taxt Search 행하는 방법에 3와 4가 있는데
    최신 AS에서는 FTS4를 사용
    매우 빠른 속도로 전체텍스트 검색을 할 수 잇음
    FTS4는 Room 2.1.0이상에서 제공
 */
// @FTS4
@Entity(tableName = "tbl_memo")
public class MemoVO {



    /*
    PK 지정된 숫자형 칼럼에 auto increment를 부여하는 속성
    SQLight에서 FTS라는 패턴을 지원하는 DB형식
    FTS : full Text Search
    FTS를 사용하려면 id 칼럼이 반드시 int형
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    private int id;

    @ColumnInfo(name = "m_date")
    private String m_date;

    @ColumnInfo(name = "m_time")
    private String m_time;

    @ColumnInfo
    private String m_text;
}
