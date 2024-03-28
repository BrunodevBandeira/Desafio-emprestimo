package br.com.emprestimos.dto.response;

import lombok.Data;

@Data
public class LoanResponseDTO {
    private String type;
    private Double interestRate;

    public LoanResponseDTO() {}

    public LoanResponseDTO(String type, Double interestRate) {
        this.type = type;
        this.interestRate = interestRate;
    }
}
