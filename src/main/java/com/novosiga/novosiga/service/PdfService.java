package com.novosiga.novosiga.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Renderiza um template Thymeleaf em HTML e converte para um array de bytes PDF.
     */
    public byte[] gerarPdfDeHtml(String templateName, Context context) throws Exception {
        // 1. Processa o template HTML com as variáveis do Thymeleaf
        String htmlProcessado = templateEngine.process(templateName, context);

        // 2. Converte o HTML em PDF com OpenHTMLtoPDF
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlProcessado, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        }
    }
}
