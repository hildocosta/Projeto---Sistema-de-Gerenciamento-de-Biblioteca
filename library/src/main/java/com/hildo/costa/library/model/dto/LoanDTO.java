package com.hildo.costa.library.model.dto;

import java.time.LocalDate;
import java.util.List;

public record LoanDTO(
        String id,
        int idUsuario,
        List<LoanItemDTO> livros,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
