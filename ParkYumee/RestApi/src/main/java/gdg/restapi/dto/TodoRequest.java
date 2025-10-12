package gdg.restapi.dto;

import lombok.Builder;
import lombok.Getter;

public record TodoRequest(String title, String content, boolean isWeekly){}

