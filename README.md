# OrbitShield API

## Descrição do Projeto

**OrbitShield** é uma API REST B2B para cadastro e monitoramento de satélites artificiais em órbita. Empresas clientes registram seus satélites na plataforma e consultam alertas de risco de colisão e status orbital em tempo real.

O projeto implementa uma arquitetura em camadas com autenticação via API Key, seguindo princípios RESTful e boas práticas de desenvolvimento.

---

## ODS — Objetivo de Desenvolvimento Sustentável

**ODS 9 — Indústria, Inovação e Infraestrutura**

O projeto se alinha a este ODS por:
- Promover inovação tecnológica no monitoramento espacial
- Garantir resiliência de infraestrutura crítica (satélites)
- Facilitar infraestrutura de informação confiável para o setor espacial

---

## Integrantes

| Nome            | RM     |
|-----------------|--------|
| Arthur Pagani   | RM554510 |
| Diogo Leles     | RM558487 |
| Felipe Oliveira | RM559085 |
| Ryan Brito      | RM554497 |
| Vitor Chaves    | RM557067 |

---

## Arquitetura

### Diagrama de Arquitetura
[![](https://mermaid.ink/img/pako:eNqFVt1u2zYYfRVCAYYUkx35XzKGArIsx0GLLjDjrVi9C1qiYqKy5FJUljQNsItitx3aAsN6M3Q32wsM2NVu8iZ5ge0R9lGUJTmuXcEQSH7nfNQ5JD_6WvNin2p97ZyT1QKdDWYRgidJ52rACRmNBH020_777c3v626CBs3BTPtegeXjNACCWSLokiD3UlAexV_N-dHDQ7wiHn2qIxfb9Xr9wSarCazTOBFLEqEj5KU8LOI08mfRva-xT0-A8DWfM4EXjIa-HEF3P75HeMVZdI4GcSxQq97emMVnnHqCxVEhTz5la3Ty-MydZBLf_4zsFXtEr-xULEYsBB2ZihMQzT26EgSJ2CcJgh-nL1KWsNs_bv-iSYb6hoTMJ-hpDb6qBknQIR7btWanu6m6bJU2x5HgcRhSnmTf8e5jZQg9JleUb6SQD3ak5UTQMGSClvAtoCuBDsTga-PIvYAV3IPG0yxvOk88zlbStj1gW2JtiOzLOJagMSWhWOxAZWu9xx5M-QXzqPTm7sOv__79Zj2yyxpctSbHbvuCt3zZBcVTfM-UXUgbrx35FOJzQid0FSdMxJxRtRHe_lOOXe1SO6mqLeHbgidbgveg8XRyT_MesP1IgtXZ2QH7nHb3Uh4wmEYp__BnOYLGJPJDOOBb8x67Y0Afh_GchAU8Q3_CqMmTEYAnNIlTOMxPYjGK08gvaFt4x838ioKQeWI3bCph04hAyYCFe0l3ZCz0Fw31Hg6eHUrBv7yWG3vcREMiyJwkVNVPFtWWdAlmQhF5kKfD39rHx3nJevca4R_I-TnUielJRjlKVL-WsvpCLFVJVUSngb6AqotqtYev4EyenZ1i9GVZsGbaq7waKrhq5-iL24-yvElMpVxtAusZkkU5Ft399Ba1jUbGaSjo2FnDErpEAdRYHlfjuUJHzgoHWXVd1XXzLtQoFZ7mA7bq23gjB1agXIyrumttwM1z5AN20QePth2QW3wju4LBSzEgnMGG-R0jb6Ysn1qr9Qp4IUmSIQ2gIa9SaUDYP6Bm0A5MPQEvntP-QbNhdUct3YvDmPcPDMO4xw2ymynnBkHQokbBHY0s0zB2cz3Bw3LWDrUKZtuxR509zOTCW0_Zop2gUxAtp9kb7CFyKAhrpkfb1CuYrtVwu3uE-vM1rxNUZ-wavaE52M2jl17pjkkbFXechtHbIlbosBF1OCFqearj-VZQ3lcD2NFdR4c9qduODttbOrwRx7qLIY512GJg4kZsorsTiE10uX-kUdXocAAOVAeg3OlQxXTH1aeuVKnp8M-N-Vpf8JTq2pLyJZFd7VrSZppY0CVcQX1o-oQ_n2mz6AY4KxJ9F8fLNY3H6flC6wckTKCXrny4TIaMQF0uIVC1KHegYAqt3zSzFFr_WrvU-pZZ77TMntnpWVYPTruuXQHEqjcNy7DMbq_R6fZudO1lNqFRN3vtlmU1zHbPNLrtzs3_DNMtXw?type=png)](https://mermaid.live/edit#pako:eNqFVt1u2zYYfRVCAYYUkx35XzKGArIsx0GLLjDjrVi9C1qiYqKy5FJUljQNsItitx3aAsN6M3Q32wsM2NVu8iZ5ge0R9lGUJTmuXcEQSH7nfNQ5JD_6WvNin2p97ZyT1QKdDWYRgidJ52rACRmNBH020_777c3v626CBs3BTPtegeXjNACCWSLokiD3UlAexV_N-dHDQ7wiHn2qIxfb9Xr9wSarCazTOBFLEqEj5KU8LOI08mfRva-xT0-A8DWfM4EXjIa-HEF3P75HeMVZdI4GcSxQq97emMVnnHqCxVEhTz5la3Ty-MydZBLf_4zsFXtEr-xULEYsBB2ZihMQzT26EgSJ2CcJgh-nL1KWsNs_bv-iSYb6hoTMJ-hpDb6qBknQIR7btWanu6m6bJU2x5HgcRhSnmTf8e5jZQg9JleUb6SQD3ak5UTQMGSClvAtoCuBDsTga-PIvYAV3IPG0yxvOk88zlbStj1gW2JtiOzLOJagMSWhWOxAZWu9xx5M-QXzqPTm7sOv__79Zj2yyxpctSbHbvuCt3zZBcVTfM-UXUgbrx35FOJzQid0FSdMxJxRtRHe_lOOXe1SO6mqLeHbgidbgveg8XRyT_MesP1IgtXZ2QH7nHb3Uh4wmEYp__BnOYLGJPJDOOBb8x67Y0Afh_GchAU8Q3_CqMmTEYAnNIlTOMxPYjGK08gvaFt4x838ioKQeWI3bCph04hAyYCFe0l3ZCz0Fw31Hg6eHUrBv7yWG3vcREMiyJwkVNVPFtWWdAlmQhF5kKfD39rHx3nJevca4R_I-TnUielJRjlKVL-WsvpCLFVJVUSngb6AqotqtYev4EyenZ1i9GVZsGbaq7waKrhq5-iL24-yvElMpVxtAusZkkU5Ft399Ba1jUbGaSjo2FnDErpEAdRYHlfjuUJHzgoHWXVd1XXzLtQoFZ7mA7bq23gjB1agXIyrumttwM1z5AN20QePth2QW3wju4LBSzEgnMGG-R0jb6Ysn1qr9Qp4IUmSIQ2gIa9SaUDYP6Bm0A5MPQEvntP-QbNhdUct3YvDmPcPDMO4xw2ymynnBkHQokbBHY0s0zB2cz3Bw3LWDrUKZtuxR509zOTCW0_Zop2gUxAtp9kb7CFyKAhrpkfb1CuYrtVwu3uE-vM1rxNUZ-wavaE52M2jl17pjkkbFXechtHbIlbosBF1OCFqearj-VZQ3lcD2NFdR4c9qduODttbOrwRx7qLIY512GJg4kZsorsTiE10uX-kUdXocAAOVAeg3OlQxXTH1aeuVKnp8M-N-Vpf8JTq2pLyJZFd7VrSZppY0CVcQX1o-oQ_n2mz6AY4KxJ9F8fLNY3H6flC6wckTKCXrny4TIaMQF0uIVC1KHegYAqt3zSzFFr_WrvU-pZZ77TMntnpWVYPTruuXQHEqjcNy7DMbq_R6fZudO1lNqFRN3vtlmU1zHbPNLrtzs3_DNMtXw)

### Diagrama de Camadas
[![](https://mermaid.ink/img/pako:eNqdVd1u2zYUfhWCVy0WJ_KPbMsYijm2A6_LatfWimL1Lmjp1CYmkRpJGUmDAN079AXaXRQYsKtiT-A36ZPsiLL10xVoEsGQef75fTw8uqGBDIEO6EaxZEsuFytB8NHpOldMhFEsZK9W9LAiU9-fr-hvuV_2LCbP0byAP1LQxpq_X6uzJ9-RKbAQlD5I5zK8Jk-Xs2dFMIhwJb6oN2IxFmliwnxFmuTz23fkgkdGyVrZC_QZJvwnuB6mZps5gLKlPr_785u_3PHtBzK5QlScvGwM5z82MFdhWE6HjZbbJVumt4VyJIVOI8NIXngBidTcSFUJA7QGUhi4MpI88rcKSbiUAYse13Fni69ib5XYWxY7FkXsUQSqhn_kLy4z18Kq749-AQGswZ4ZUfkBFrYxaFCcRfwNI2N_Rhb_s0ewYSRhiiFqteMBVPIiKeIYpxOkDSqBr7kAog0zqSZZ-92RmXbJTNsycyhbo2X5YoRuB8uDKMGSmoRABGz2nwIuC9MLJCNk-4_7f8E6hDLe_yMqDqP9-yhII5kZNeyQPlN2xkxZ_lSW4K8y5pKJ_UdG4CqAPPMdyeiUZHQsGWUzfnE75zN7PQ9W_hBSRotfxmTHGXk6HxbK5ylk2UiQaiNjbJOQ6co9iEm0_7ThAatyeUdwbgnOteDm2N5cm_3fIuCshm_yzM-HEzfZZs7Iz9hQ0QMwLpmBKOIGKrcdZc2lmOxAlI2_TNc6UDwxaCmU-UCo7Wx8_urRik5bZMwMWzONffr4G-h9xYTGvtEsQlAVqZ7Yz04U3_dEeRzRZ_U7iVs_yrixHUQyyQ0TpaQ6muqkv8wu2QR71rJApkyE0X3H7w_l6BqGxfjoOA5usOM07btz0HkouY7ztU8Hfn5Io_GEXOTihRWy4ZjL2cqqcDDkGlxYRXYzjinmM6vCZrL_4_Pq8eQpTjO9P6vkqGmQklyTedMT_J7ykA6MSuGExqBilon0JnNeUbOFGBkd4DJk6vcVXYlbjEmY-FXK-BimZLrZ0sFrFmmU0iTEDh1zhq0SF1qFTIAayVQYOmi7Ngcd3NArOvD6p2673-u7Pc_rIaEn9JoOWt5py_Ecr9_tNd1u7_aEvrEVndN-r9P2vGbfbfadbrfTvP0Pe0T_yA?type=png)](https://mermaid.live/edit#pako:eNqdVd1u2zYUfhWCVy0WJ_KPbMsYijm2A6_LatfWimL1Lmjp1CYmkRpJGUmDAN079AXaXRQYsKtiT-A36ZPsiLL10xVoEsGQef75fTw8uqGBDIEO6EaxZEsuFytB8NHpOldMhFEsZK9W9LAiU9-fr-hvuV_2LCbP0byAP1LQxpq_X6uzJ9-RKbAQlD5I5zK8Jk-Xs2dFMIhwJb6oN2IxFmliwnxFmuTz23fkgkdGyVrZC_QZJvwnuB6mZps5gLKlPr_785u_3PHtBzK5QlScvGwM5z82MFdhWE6HjZbbJVumt4VyJIVOI8NIXngBidTcSFUJA7QGUhi4MpI88rcKSbiUAYse13Fni69ib5XYWxY7FkXsUQSqhn_kLy4z18Kq749-AQGswZ4ZUfkBFrYxaFCcRfwNI2N_Rhb_s0ewYSRhiiFqteMBVPIiKeIYpxOkDSqBr7kAog0zqSZZ-92RmXbJTNsycyhbo2X5YoRuB8uDKMGSmoRABGz2nwIuC9MLJCNk-4_7f8E6hDLe_yMqDqP9-yhII5kZNeyQPlN2xkxZ_lSW4K8y5pKJ_UdG4CqAPPMdyeiUZHQsGWUzfnE75zN7PQ9W_hBSRotfxmTHGXk6HxbK5ylk2UiQaiNjbJOQ6co9iEm0_7ThAatyeUdwbgnOteDm2N5cm_3fIuCshm_yzM-HEzfZZs7Iz9hQ0QMwLpmBKOIGKrcdZc2lmOxAlI2_TNc6UDwxaCmU-UCo7Wx8_urRik5bZMwMWzONffr4G-h9xYTGvtEsQlAVqZ7Yz04U3_dEeRzRZ_U7iVs_yrixHUQyyQ0TpaQ6muqkv8wu2QR71rJApkyE0X3H7w_l6BqGxfjoOA5usOM07btz0HkouY7ztU8Hfn5Io_GEXOTihRWy4ZjL2cqqcDDkGlxYRXYzjinmM6vCZrL_4_Pq8eQpTjO9P6vkqGmQklyTedMT_J7ykA6MSuGExqBilon0JnNeUbOFGBkd4DJk6vcVXYlbjEmY-FXK-BimZLrZ0sFrFmmU0iTEDh1zhq0SF1qFTIAayVQYOmi7Ngcd3NArOvD6p2673-u7Pc_rIaEn9JoOWt5py_Ecr9_tNd1u7_aEvrEVndN-r9P2vGbfbfadbrfTvP0Pe0T_yA)

### Diagrama de Sequência (Fluxo Básico da Aplicação)
Diagrama 1
[![](https://mermaid.ink/img/pako:eNqFVctu20YU_ZWLWdGxREuK9SISF9bDkJHUCiyhCQoBxpicmIOQHGZmKEgRtCuyTZAEKJpNkHQR9A-66sZ_kh9IP6F3OHpGMqoFJZLnPs65Z65mxBcBIx5R7GXGEp91OL2RNB4lgJ-USs19ntJEQzviDL-ogn8_vfmyuGXQqrR2sWc80kxa7Ie3cJryR2x6munQvtiTXMvIwt9_hrZItBRRtA84GPsG9-3jH9__fgMDJsfcZ7uwS5YKm-_dP_mN4lrI6S6w07Kw338z-XoV6FBNr6nCnBZ8IZCkGCMby7jQaXlwFmUT4UFbciphQPXtXxFHmPOkPxjCEU350bh8pKhmkXmuDmwqe7Vpiicnh1YMD-6IenAtj056jAYG86x4-uS8iCp60JfXXA9CzqLgCmH6BZte0SgNaR7QEgFiZpDQmBVAGOxwmuJP13VhvtmILY-NLPvoTrSkXEKY11yXvAM-6J0WK9Wa44d0zA5-AB0a0T14zpOgNcUcPapCJ8TLAmheG5hRc9B93G0P4R6cXfZ_BtThCinB0173sguGnAmDh_CTjey0ihhYtPmtswAF5Xq6kdkgln0uMA71NR-zh1pmO92uKDGNA7UBxoVsosEZhhIFeSx8Gh3s1e_QuNfDwi8zrvjt19s_0XuZNk35NKCLsSMGsRbaYYqhdyL-yvrHTtwkwHlCZ9jfijlE03vgYxearcCOtOitlhD4o_YX6IMl1jWm-N8BrBy4GIEJukP-JIsicBJDmE240lvJDSzvvJ9qLhIauSxO9dTZ03EO-wX1CFAPn8Z4XnMz4wzw5JlVgprAt9fvoLsx6S22Ck3orFrfQ_L8YtC9HOIXZlpTxFOxh9dKZXB8EcN5B26YpIHYS28N3rThDr01F8shp7Mxe5WKRLH18E2oqWEdswPcWimb3uomYxGZfcVi4-Ql3lWZ7zOlnAAX3MFGXF4j30keVEplXGrGZ0Gu_wyUpjpTHozIIn5ECmBS4I5B7eYF0DxGa-HMYE4K5EbygHjmjBVIzGRMzS2ZmXojokMWsxEx2XDQL0ZklJgYXMS_ChEvw6TIbkLiPaeRwrssxWrLf6XVU8kSXFFtkSWaeMeVPAfxZmRCvGbDrd5v1BvVerNZPy6VC2RKvErTrZSapWajVi9Xa_V5gbzKK5bcRv34frNZblTLjVKtdlye_wfoY1UO?type=png)](https://mermaid.live/edit#pako:eNqFVctu20YU_ZWLWdGxREuK9SISF9bDkJHUCiyhCQoBxpicmIOQHGZmKEgRtCuyTZAEKJpNkHQR9A-66sZ_kh9IP6F3OHpGMqoFJZLnPs65Z65mxBcBIx5R7GXGEp91OL2RNB4lgJ-USs19ntJEQzviDL-ogn8_vfmyuGXQqrR2sWc80kxa7Ie3cJryR2x6munQvtiTXMvIwt9_hrZItBRRtA84GPsG9-3jH9__fgMDJsfcZ7uwS5YKm-_dP_mN4lrI6S6w07Kw338z-XoV6FBNr6nCnBZ8IZCkGCMby7jQaXlwFmUT4UFbciphQPXtXxFHmPOkPxjCEU350bh8pKhmkXmuDmwqe7Vpiicnh1YMD-6IenAtj056jAYG86x4-uS8iCp60JfXXA9CzqLgCmH6BZte0SgNaR7QEgFiZpDQmBVAGOxwmuJP13VhvtmILY-NLPvoTrSkXEKY11yXvAM-6J0WK9Wa44d0zA5-AB0a0T14zpOgNcUcPapCJ8TLAmheG5hRc9B93G0P4R6cXfZ_BtThCinB0173sguGnAmDh_CTjey0ihhYtPmtswAF5Xq6kdkgln0uMA71NR-zh1pmO92uKDGNA7UBxoVsosEZhhIFeSx8Gh3s1e_QuNfDwi8zrvjt19s_0XuZNk35NKCLsSMGsRbaYYqhdyL-yvrHTtwkwHlCZ9jfijlE03vgYxearcCOtOitlhD4o_YX6IMl1jWm-N8BrBy4GIEJukP-JIsicBJDmE240lvJDSzvvJ9qLhIauSxO9dTZ03EO-wX1CFAPn8Z4XnMz4wzw5JlVgprAt9fvoLsx6S22Ck3orFrfQ_L8YtC9HOIXZlpTxFOxh9dKZXB8EcN5B26YpIHYS28N3rThDr01F8shp7Mxe5WKRLH18E2oqWEdswPcWimb3uomYxGZfcVi4-Ql3lWZ7zOlnAAX3MFGXF4j30keVEplXGrGZ0Gu_wyUpjpTHozIIn5ECmBS4I5B7eYF0DxGa-HMYE4K5EbygHjmjBVIzGRMzS2ZmXojokMWsxEx2XDQL0ZklJgYXMS_ChEvw6TIbkLiPaeRwrssxWrLf6XVU8kSXFFtkSWaeMeVPAfxZmRCvGbDrd5v1BvVerNZPy6VC2RKvErTrZSapWajVi9Xa_V5gbzKK5bcRv34frNZblTLjVKtdlye_wfoY1UO)

Diagrama 2
[![](https://mermaid.ink/img/pako:eNptVc1u20YQfpXBnuiKkiXF-iNaG5REx0Icq5AM9E-BsCI30sIUl91dClYN34JeU6QBguRStD30FXrqJW-SF2gfobNcSrYi6SCB3O_7Zuab2dEdCUXEiEcU-zFjScj6nM4lXU4SwE9KpeYhT2mioRdzhj9UwX-_vf6jeGTQrXf3sec81kxa7NtfwE_5M7b2M72wBwfEtYwt_NffwY-Z1D2RaCni-BB6vAoN-NOH9__-_drCx0yueMgOYLPZiKXCir_5xzyrUPJUc5GYg31GsNI7jB5mwRXCgxVWfJjT71r4u1cmo4s69KmmM6owIQu-EuiVWKEp1ji33_XgPM5uhYcBEpXFmkpbCgo5T4NrOKYpP17Vjql5qY6sjv22GuXT05I11IN9wpczeXx6wWhkjr8t-18PytgED4ZyxvV4wVkcTTVT-oatpzROF9RKW0GU3ihj2zAWDzE9Z3zhl-uNJpQgFuImS48-45RMHz0Y4SyhYx__-vgn2rjhRxQcOwmQCAixv-xWi926kG5ksMEezJn2Q81XLHdFOQUSzxCSI4azfMpyzUEE0YMqONcLibVfipDGOyEsvVSMhQcveRJ1136h4WzENsEszDBMw8bBZdC7BkU1w5nQbMojOB8Nn4N6NFXwzUUwCkxaU-MtQr6CMyvX75ZRqrwN_gMqlWuuESzXX-yGzIGmykuuNIWI7Yyu2q8pBwe3WlIuH1IcRAoUm2c8Egc4pWLYNz6MH2iDxE-iEVMiXrHonMaKOY9FC4MK_mcGfWFdCTc3Z8rM1ckn0pqz4-DgCpwzF86OcoB_1QdZhEXnXprI-cFw1A9G0P0Oy8F7xPUa-sG450IYC4VzPKVpKgUNF-CPezt2b2vcOrl7pXcr2fqenwkFiZlim9FhE3M0XmLMyszjp5_f7O0MlWLLGPSvh-qBaSLZC7NNjNoFcOBOWGCQrIwxEtjSbNWNcEVlYciUciJcO0ePeHmIfFl4UK9WYfgst_IOMJ7OlAcTUlAnxAXDxqGsVCovXNB8ia7SZQr3xCVzySPiaZkxlyyZXFLzSO5MqAnRC7ZkE2LUIipvJmSSGA5uxu-FWG5oUmTzBfHyhrokSzHa5t9m-1ayBPdVT2SJJl67lWsQ747cEq_TrjSetFvtRqvTaZ1Uay5ZE6_eqdSrnWqn3WzVGs3WvUt-yiNWK-3WyZNOp9Zu1NrVZvOkdv8_UWVSLw?type=png)](https://mermaid.live/edit#pako:eNptVc1u20YQfpXBnuiKkiXF-iNaG5REx0Icq5AM9E-BsCI30sIUl91dClYN34JeU6QBguRStD30FXrqJW-SF2gfobNcSrYi6SCB3O_7Zuab2dEdCUXEiEcU-zFjScj6nM4lXU4SwE9KpeYhT2mioRdzhj9UwX-_vf6jeGTQrXf3sec81kxa7NtfwE_5M7b2M72wBwfEtYwt_NffwY-Z1D2RaCni-BB6vAoN-NOH9__-_drCx0yueMgOYLPZiKXCir_5xzyrUPJUc5GYg31GsNI7jB5mwRXCgxVWfJjT71r4u1cmo4s69KmmM6owIQu-EuiVWKEp1ji33_XgPM5uhYcBEpXFmkpbCgo5T4NrOKYpP17Vjql5qY6sjv22GuXT05I11IN9wpczeXx6wWhkjr8t-18PytgED4ZyxvV4wVkcTTVT-oatpzROF9RKW0GU3ihj2zAWDzE9Z3zhl-uNJpQgFuImS48-45RMHz0Y4SyhYx__-vgn2rjhRxQcOwmQCAixv-xWi926kG5ksMEezJn2Q81XLHdFOQUSzxCSI4azfMpyzUEE0YMqONcLibVfipDGOyEsvVSMhQcveRJ1136h4WzENsEszDBMw8bBZdC7BkU1w5nQbMojOB8Nn4N6NFXwzUUwCkxaU-MtQr6CMyvX75ZRqrwN_gMqlWuuESzXX-yGzIGmykuuNIWI7Yyu2q8pBwe3WlIuH1IcRAoUm2c8Egc4pWLYNz6MH2iDxE-iEVMiXrHonMaKOY9FC4MK_mcGfWFdCTc3Z8rM1ckn0pqz4-DgCpwzF86OcoB_1QdZhEXnXprI-cFw1A9G0P0Oy8F7xPUa-sG450IYC4VzPKVpKgUNF-CPezt2b2vcOrl7pXcr2fqenwkFiZlim9FhE3M0XmLMyszjp5_f7O0MlWLLGPSvh-qBaSLZC7NNjNoFcOBOWGCQrIwxEtjSbNWNcEVlYciUciJcO0ePeHmIfFl4UK9WYfgst_IOMJ7OlAcTUlAnxAXDxqGsVCovXNB8ia7SZQr3xCVzySPiaZkxlyyZXFLzSO5MqAnRC7ZkE2LUIipvJmSSGA5uxu-FWG5oUmTzBfHyhrokSzHa5t9m-1ayBPdVT2SJJl67lWsQ747cEq_TrjSetFvtRqvTaZ1Uay5ZE6_eqdSrnWqn3WzVGs3WvUt-yiNWK-3WyZNOp9Zu1NrVZvOkdv8_UWVSLw)

### Modelo de Dados (Entidades e Relacionamentos)
[![](https://mermaid.ink/img/pako:eNqlVN1u2jAUfhXLV5tEUWj5SXJHIWujUqgKdNqEFJn4DLwmduQ4rIzyNLuY-gq75cV2ElraLqCtWi4cHx9_3_H5jo9XNFQcqEtBdwWbaRZPJMGvfeUHF94nstqa-Tce-10iOLm6eF4bGi3kjNzCMpizdE4mdHjePjpuNAlnJJyzBUxoaXeo4oTJJW724kRDyghXku1FnCoVAZOEhUagZ7c-EjGkhsUJCTUwAzxgZutdb3_bcdgeeb2eP_L-MQ_JYiDjCzxZX-Fs80uKUO3JQH2ToIPHPMosSjMeYIQJxUhwZ0BLRd6pJBSYZ_T-JaEns5goPRUmMMsEENLzBuSeXBbjWTGee4OXkK7KphEQFhlhMg7BbVzyCRlGQjKD8ZDxTLMsLQVF-UyWorvdGfk3Hsbx-7tp1xtcn6Ju3Zeww6K_9mYJP1ySzgALMvQH_cC78fqjvxWmWEuRLoqEgVzUD-WyqelXCE1QVG9CB2gZRdBgm5-MK71HvESrKZsK5MyvoVW1CCO1qnUg20ilOA1YgjAWzktsXOBGGb4uxVZkWIDeBukNPhZ17frjy7yo_tk5_jrX_sjvtHv7Lj22hooWwN9-7cenQyS-GqHM_ydw4WWJCPIO_8P35h68vz86UqvSDXBRHAMxQamkUSnhgC9EJNLNj13rlTheZZgTbB5Q61kmuCLJc8WfXrFDqBwCefabhzx7bBJaoTMtOHWNzqBCY9Axy01ayIgHnUOML1QO5kzf5oHWiMF34LNS8RNMq2w2p-4XFqVobfvh8XndrWqQHHRHZdJQ16kXHNRd0Tu07GrjxG7ZjZbjtOpWrUKX1D12qseWYzl2s1VrNFvrCv1eRLSqdqt-4jg1u1GzrWazXlv_BoNrwAE?type=png)](https://mermaid.live/edit#pako:eNqlVN1u2jAUfhXLV5tEUWj5SXJHIWujUqgKdNqEFJn4DLwmduQ4rIzyNLuY-gq75cV2ElraLqCtWi4cHx9_3_H5jo9XNFQcqEtBdwWbaRZPJMGvfeUHF94nstqa-Tce-10iOLm6eF4bGi3kjNzCMpizdE4mdHjePjpuNAlnJJyzBUxoaXeo4oTJJW724kRDyghXku1FnCoVAZOEhUagZ7c-EjGkhsUJCTUwAzxgZutdb3_bcdgeeb2eP_L-MQ_JYiDjCzxZX-Fs80uKUO3JQH2ToIPHPMosSjMeYIQJxUhwZ0BLRd6pJBSYZ_T-JaEns5goPRUmMMsEENLzBuSeXBbjWTGee4OXkK7KphEQFhlhMg7BbVzyCRlGQjKD8ZDxTLMsLQVF-UyWorvdGfk3Hsbx-7tp1xtcn6Ju3Zeww6K_9mYJP1ySzgALMvQH_cC78fqjvxWmWEuRLoqEgVzUD-WyqelXCE1QVG9CB2gZRdBgm5-MK71HvESrKZsK5MyvoVW1CCO1qnUg20ilOA1YgjAWzktsXOBGGb4uxVZkWIDeBukNPhZ17frjy7yo_tk5_jrX_sjvtHv7Lj22hooWwN9-7cenQyS-GqHM_ydw4WWJCPIO_8P35h68vz86UqvSDXBRHAMxQamkUSnhgC9EJNLNj13rlTheZZgTbB5Q61kmuCLJc8WfXrFDqBwCefabhzx7bBJaoTMtOHWNzqBCY9Axy01ayIgHnUOML1QO5kzf5oHWiMF34LNS8RNMq2w2p-4XFqVobfvh8XndrWqQHHRHZdJQ16kXHNRd0Tu07GrjxG7ZjZbjtOpWrUKX1D12qseWYzl2s1VrNFvrCv1eRLSqdqt-4jg1u1GzrWazXlv_BoNrwAE)

### Fluxograma de Autenticação
[![](https://mermaid.ink/img/pako:eNp9UcFO20AQ_ZXRnloUJzZxAokKKCSAA6iKSipVxT1MvEu8qr3rru0ACTn21k9oTz1U_YVe8yf9ko43JURUqg8rz-y8N--9XbBIc8G67CbRt1GMpoDxIFRAX-86ZG_Ep1LmcvVj9V1DMB6PwIhITCTHkH0AxzmE40XIRljEsPoJ2erXJJGRPno1MY3DRiwwKeIaNPJbnE6FcUrZ2NmprS9nzQZm0uE6yqkZsuV66fo8rqgfrmT6AP3rij_P0QByiaoQFv8iFyncyKQw-iVJ2Ya9Jq0PMCBdgUAuDLxzeqOhcyHuLTIzIhdEc_Rs52ALfEJLf3_7Ar7rwVuFZRFrI-eCWwIiAyIDmVMYFI8RfKNg8CT8lDhO7gqD0sAME22Aa4itos34-jy1QZ7RfB-TqEzI6lXQc3Zb7QpisRvEmZ0NKnOYxyBUpBXt4NpKUxomSK3n3oItb8P_eRsqWic5kFqpMCrkTMBfvxsJwZPHc9LRyyTd1tfDluTgAApT_hPw-baI7ZaluiBZPZPiXCjyvyZ99DSODeV2qSNMNioubBCXlZmvn6FPKUhVEjJDgxanbdPoJLGBsxqbGslZt5JWY6mgXVXJFhVfyIpYpCJkXfrlaD6GLFRLwmSo3mudPsKMLqcx695gklNVZhwLMZA4NZhuukYoeuO-LlXBul6naUlYd8HuqGy7ddf33U5r393bc_e8Vo3dU7vebnf8Xdft7Dc9z_Obyxqb27VuveP7XqtFr9Vs77Zbrrf8A_25LEM?type=png)](https://mermaid.live/edit#pako:eNp9UcFO20AQ_ZXRnloUJzZxAokKKCSAA6iKSipVxT1MvEu8qr3rru0ACTn21k9oTz1U_YVe8yf9ko43JURUqg8rz-y8N--9XbBIc8G67CbRt1GMpoDxIFRAX-86ZG_Ep1LmcvVj9V1DMB6PwIhITCTHkH0AxzmE40XIRljEsPoJ2erXJJGRPno1MY3DRiwwKeIaNPJbnE6FcUrZ2NmprS9nzQZm0uE6yqkZsuV66fo8rqgfrmT6AP3rij_P0QByiaoQFv8iFyncyKQw-iVJ2Ya9Jq0PMCBdgUAuDLxzeqOhcyHuLTIzIhdEc_Rs52ALfEJLf3_7Ar7rwVuFZRFrI-eCWwIiAyIDmVMYFI8RfKNg8CT8lDhO7gqD0sAME22Aa4itos34-jy1QZ7RfB-TqEzI6lXQc3Zb7QpisRvEmZ0NKnOYxyBUpBXt4NpKUxomSK3n3oItb8P_eRsqWic5kFqpMCrkTMBfvxsJwZPHc9LRyyTd1tfDluTgAApT_hPw-baI7ZaluiBZPZPiXCjyvyZ99DSODeV2qSNMNioubBCXlZmvn6FPKUhVEjJDgxanbdPoJLGBsxqbGslZt5JWY6mgXVXJFhVfyIpYpCJkXfrlaD6GLFRLwmSo3mudPsKMLqcx695gklNVZhwLMZA4NZhuukYoeuO-LlXBul6naUlYd8HuqGy7ddf33U5r393bc_e8Vo3dU7vebnf8Xdft7Dc9z_Obyxqb27VuveP7XqtFr9Vs77Zbrrf8A_25LEM)

### Componentes Principais

| Componente | Responsabilidade |
|---|---|
| **Controllers** | Receber requisições HTTP, validar entrada, retornar respostas padronizadas |
| **Services** | Implementar regras de negócio, orquestração, validações de domínio |
| **Repositories** | Acesso aos dados via JPA |
| **Models/Entities** | Estruturas de dados mapeadas para tabelas do banco |
| **DTOs** | Contratos de entrada/saída da API (request/response) |
| **Filters** | Interceptar requisições para autenticação via API Key |
| **Exceptions** | Tratamento centralizado de erros |

### Responsabilidades

- **Controller**: Nunca acessa Repository diretamente; sempre passa pelo Service
- **Service**: Contém toda lógica de negócio e validações
- **Repository**: Interface pura de persistência; sem lógica
- **Model**: POJO simples; sem lógica de negócio

---

## Stack Tecnológico

- **Java 21 LTS** - Linguagem de programação
- **Spring Boot 3.4.3** - Framework web
- **Spring Data JPA** - ORM e persistência
- **H2 Database** - Banco de dados em memória (desenvolvimento)
- **SpringDoc OpenAPI 2.8.5** - Documentação Swagger/OpenAPI
- **Gradle** - Gerenciador de dependências
- **SLF4J / Logback** - Logging

---

## Como Executar Localmente

### Pré-requisitos

- Java 21 JDK instalado
- Gradle instalado (ou use `./gradlew` do projeto)
- Git

### Passos

1. **Clone o repositório**
   ```bash
   git clone https://github.com/GS-OrbitShield/GS-SOA.git
   cd GS-SOA
   ```

2. **Compile e inicie a aplicação**
   ```bash
   ./gradlew bootRun
   ```
   
   ou (Windows):
   ```powershell
   .\gradlew.bat bootRun
   ```

3. **Verifique se a aplicação iniciou**
   ```bash
   curl http://localhost:8080/health
   ```
   
   Resposta esperada:
   ```json
   {
     "status": "UP",
     "service": "OrbitShield API"
   }
   ```

4. **Acesse a documentação Swagger**
   - URL: http://localhost:8080/swagger-ui.html

### API Keys de Teste

Use uma das seguintes CHAVES nos testes (o hash é apenas um exemplo de como é salvo, não deve ser usado diretamente):

| Chave | Empresa | Hash SHA-256 |
|-------|---------|------|
| `OrbitShield_testkey_alpha` | SpaceX | `93e08d75d40523d42bac5777cd4bab24147f0d12287fffc22bf4cbad4b7034be` |
| `OrbitShield_testkey_beta` | ESA | `d34d4715554805a55ba4b0f69e6d749ccf6f243ef9f4e35d0db1b951ebaeb8ed` |

---

## Endpoints da API

Base URL: `http://localhost:8080/api/v1`

### Satélites

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/satellites` | Listar satélites (paginado) | 200 |
| `GET` | `/satellites/{id}` | Obter satélite por ID | 200/404 |
| `POST` | `/satellites` | Criar novo satélite | 201/400/409 |
| `PUT` | `/satellites/{id}` | Atualizar satélite | 200/404/409 |
| `DELETE` | `/satellites/{id}` | Deletar satélite | 204/404 |

### Eventos de Colisão

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/satellites/{satelliteId}/events` | Listar eventos de um satélite | 200/404 |
| `GET` | `/events/{id}` | Obter evento específico | 200/404 |
| `POST` | `/satellites/{satelliteId}/events` | Criar novo evento de colisão | 201/400/404 |
| `PUT` | `/events/{id}/resolve` | Marcar evento como resolvido | 200/404 |
| `DELETE` | `/events/{id}` | Deletar evento | 204/404 |

### Subscriptions (Seguir Satélite)

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/subscriptions` | Listar satélites seguidos | 200 |
| `POST` | `/subscriptions` | Seguir um satélite | 201/404/409 |
| `DELETE` | `/subscriptions/{satelliteId}` | Deixar de seguir | 204/404 |

### Alertas

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/alerts` | Listar alertas ativos dos satélites seguidos | 200 |

### Utilitários

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| `GET` | `/health` | Health check (sem autenticação) | 200 |
| `GET` | `/swagger-ui.html` | Documentação interativa | 200 |

---

## Exemplos de Requisições

### 1. Health Check (sem autenticação)
```bash
curl -X GET http://localhost:8080/health
```

### 2. Listar Satélites (com autenticação)
```bash
curl -X GET http://localhost:8080/api/v1/satellites?page=0&size=20 \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (200 OK):**
```json
{
  "status": "success",
  "data": [
    {
      "id": "bbb00000-0000-0000-0000-000000000001",
      "name": "StarLink-1234",
      "ownerCompany": "SpaceX",
      "noradId": "48274",
      "orbitType": "LEO",
      "altitudeKm": 550.0,
      "inclination": 53.0,
      "status": "ACTIVE",
      "createdAt": "2026-05-25T10:00:00Z",
      "updatedAt": "2026-05-25T10:00:00Z"
    }
  ],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 3,
    "totalPages": 1
  },
  "timestamp": "2026-05-25T10:00:00Z"
}
```

### 3. Criar Satélite
```bash
curl -X POST http://localhost:8080/api/v1/satellites \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "GOES-18",
    "ownerCompany": "NOAA",
    "noradId": "45749",
    "orbitType": "GEO",
    "altitudeKm": 35786.0,
    "inclination": 0.5,
    "status": "ACTIVE"
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "name": "GOES-18",
    "ownerCompany": "NOAA",
    "noradId": "45749",
    "orbitType": "GEO",
    "altitudeKm": 35786.0,
    "inclination": 0.5,
    "status": "ACTIVE",
    "createdAt": "2026-05-25T10:05:00Z",
    "updatedAt": "2026-05-25T10:05:00Z"
  },
  "timestamp": "2026-05-25T10:05:00Z"
}
```

### 4. Criar Evento de Colisão
```bash
curl -X POST http://localhost:8080/api/v1/satellites/bbb00000-0000-0000-0000-000000000001/events \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "objectName": "Debris-2020-055",
    "probability": 0.05,
    "closestApproach": "2026-06-01T14:30:00Z",
    "distanceKm": 0.5
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "satelliteId": "bbb00000-0000-0000-0000-000000000001",
    "satelliteName": "StarLink-1234",
    "objectName": "Debris-2020-055",
    "probability": 0.05,
    "closestApproach": "2026-06-01T14:30:00Z",
    "distanceKm": 0.5,
    "severity": "HIGH",
    "resolved": false,
    "createdAt": "2026-05-25T10:10:00Z"
  },
  "timestamp": "2026-05-25T10:10:00Z"
}
```

### 5. Seguir um Satélite (Subscribe)
```bash
curl -X POST http://localhost:8080/api/v1/subscriptions \
  -H "X-API-Key: OrbitShield_testkey_alpha" \
  -H "Content-Type: application/json" \
  -d '{
    "satelliteId": "bbb00000-0000-0000-0000-000000000002"
  }'
```

**Resposta (201 Created):**
```json
{
  "status": "success",
  "data": {
    "id": "uuid-gerado",
    "satelliteId": "bbb00000-0000-0000-0000-000000000002",
    "satelliteName": "Sentinel-2A",
    "createdAt": "2026-05-25T10:15:00Z"
  },
  "timestamp": "2026-05-25T10:15:00Z"
}
```

### 6. Obter Alertas Ativos
```bash
curl -X GET http://localhost:8080/api/v1/alerts \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (200 OK):**
```json
{
  "status": "success",
  "data": [
    {
      "id": "ccc00000-0000-0000-0000-000000000001",
      "satelliteId": "bbb00000-0000-0000-0000-000000000001",
      "satelliteName": "StarLink-1234",
      "objectName": "Debris-2019-006",
      "probability": 0.032,
      "closestApproach": "2026-06-01T14:30:00Z",
      "distanceKm": 0.8,
      "severity": "HIGH",
      "resolved": false,
      "createdAt": "2026-05-25T10:00:00Z"
    }
  ],
  "timestamp": "2026-05-25T10:20:00Z"
}
```

### 7. Erro — Satélite Não Encontrado (404)
```bash
curl -X GET http://localhost:8080/api/v1/satellites/id-inexistente \
  -H "X-API-Key: OrbitShield_testkey_alpha"
```

**Resposta (404 Not Found):**
```json
{
  "status": "error",
  "error": {
    "code": "SATELLITE_NOT_FOUND",
    "message": "Satellite with id 'id-inexistente' not found.",
    "path": "/api/v1/satellites/id-inexistente"
  },
  "timestamp": "2026-05-25T10:25:00Z"
}
```

### 8. Erro — API Key Ausente (401)
```bash
curl -X GET http://localhost:8080/api/v1/satellites
```

**Resposta (401 Unauthorized):**
```json
{
  "status": "error",
  "error": {
    "code": "UNAUTHORIZED",
    "message": "API Key is required.",
    "path": "/api/v1/satellites"
  },
  "timestamp": "2026-05-25T10:30:00Z"
}
```

---

## Respostas às Perguntas Discursivas

### Pergunta 1: Escalabilidade com Milhares de Usuários Simultâneos

Os principais desafios seriam:

**(a) Concorrência no Banco de Dados**  
Um banco H2/SQLite não suporta escrita concorrente em alta carga. Seria necessário migrar para um banco ACID (Como PostgreSQL ou Cassandra da AWS) para distribuir com read-replicas e write-replicas, alta disponibilidade e integridade dos dados.

**(b) Latência de Autenticação**  
A validação de API Key em cada requisição implica um SELECT no banco. Seria necessário adicionar **cache distribuído (Redis)** para as chaves, diminuindo a latência de lookup de ms para µs.

**(c) Escalabilidade Horizontal**  
A aplicação monolítica precisaria ser replicada atrás de um **load balancer** (como o da AWS). Isso exige que o estado não fique armazenado na memória local da instância. O estado atual (contexto da API Key) está em ThreadLocal, o que é aceitável para uma única instância, mas requer redesenho para múltiplas instâncias.

**(d) Rate Limiting e Proteção**  
Sem controle de taxa, um único cliente poderia monopolizar recursos. Seria necessário implementar **rate limiting** via biblioteca como Resilience4j ou Spring Cloud Gateway, limitando requisições por API Key.

**(e) Observabilidade**  
Em escala, seria difícil debugar problemas sem instrumentação. Seria necessário adicionar **métricas (Micrometer)**, **rastreamento distribuído (OpenTelemetry)** e logs estruturados.

---

### Pergunta 2: Melhorias Futuras na Arquitetura

(a) **Substituir SQLite/H2 por um banco que suporte transações ACID (como Cassandra da AWS)**  
Para suporte a transações ACID mais robustas, resiliência e escalabilidade em produção, configurando para conseguir um alto nível de fidelidade nos dados salvos, e com alta disponibilidade.

(b) **Cache Distribuído (Redis)**  
Para armazenar API Keys validadas e consultas frequentes de satélites, reduzindo latência.

(c) **Implementar Eventos Assíncronos**  
Ao invés de o cliente fazer polling em `/alerts`, emitir notificações via **webhooks** ou **message broker (Kafka/RabbitMQ)**. Quando um novo evento de colisão é criado, seria publicado em um tópico que subscribers consumem em tempo real.

(d) **Módulo de Cálculo Orbital Real**  
Consumir dados TLE (Two-Line Elements) reais de Space-Track.org e calcular probabilidades de colisão automaticamente, em vez de depender da entrada manual.

(e) **Autenticação OAuth2 / OIDC**  
Para cenários mais sofisticados de autenticação, integrando com provedores como Auth0 ou Keycloak.

(f) **Observabilidade**  
Adicionar métricas (Micrometer + Prometheus), rastreamento (OpenTelemetry + Jaeger) e dashboards (Grafana).

(g) **Versionamento de API**  
Usar versionamento de endpoint (`/api/v2`, `/api/v3`) para evolução sem quebrar clientes existentes.

---

### Pergunta 3: Evolução para Arquitetura Distribuída (Microsserviços)

A evolução natural seria decompor em microsserviços alinhados aos **bounded contexts**:

**(a) Satellite Service**  
- Gerencia cadastro, metadados e status dos satélites
- Endpoints: CRUD de satélites, busca, filtros
- Banco de dados: PostgreSQL dedicado
- Expõe eventos: `SatelliteCreated`, `SatelliteUpdated`, `SatelliteDeleted`

**(b) Collision Service**  
- Responsável pelo cálculo e armazenamento de eventos de colisão
- Potencialmente alimentado por um job externo que consome dados TLE e calcula probabilidades
- Endpoints: CRUD de eventos, listagem por satélite
- Banco de dados: PostgreSQL dedicado (otimizado para time-series)
- Expõe eventos: `CollisionEventCreated`, `CollisionResolved`

**(c) Subscription & Alert Service**  
- Gerencia assinaturas (quem segue qual satélite)
- Consome eventos de colisão e os distribui para subscribers
- Expõe eventos: `AlertGenerated`, `AlertDismissed`
- Suporta múltiplos canais: webhooks, email, SMS, SSE (Server-Sent Events)
- Banco de dados: PostgreSQL

**(d) API Gateway**  
- Ponto de entrada único para clientes
- Responsável por autenticação (API Key com cache distribuído)
- Rate limiting global
- Roteamento inteligente entre serviços
- Stack: Spring Cloud Gateway ou Kong

**(e) Message Broker (Event Bus)**  
- Kafka ou RabbitMQ para desacoplamento entre serviços
- Tópicos: `satellite-events`, `collision-events`, `alert-events`
- Garante entrega at-least-once e ordering por satélite

**(f) Padrão Database per Service**  
- Cada microsserviço tem seu próprio banco de dados
- Evita acoplamento via banco e permite evolução independente

**Fluxo de Colaboração**  
```
Cliente → API Gateway → (autenticação) → Satellite Service │ Collision Service
         (rate limiting)                    ↓                    ↓
                                       Message Broker (Kafka)
                                       Subscription Service ← Alertas → Cliente
                                       (webhooks/SSE)
```

---

## Debugging e Testes

### Testar Sem Autenticação (Health)
```bash
curl http://localhost:8080/health -i
```

### Testar com Autenticação Inválida
```bash
curl http://localhost:8080/api/v1/satellites \
  -H "X-API-Key: invalid_key" -i
```

### Ver Logs da Aplicação
A aplicação usa SLF4J. Logs são exibidos no console ao iniciar.

### Acessar H2 Console (Admin)
Se desejar inspecionar o banco de dados em tempo de execução:
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:OrbitShield`
- User: `sa`
- Password: (deixar em branco)

---

## Deploy e CI/CD

### Build da Aplicação
```bash
./gradlew clean build
```
 
Gera um JAR executável em `build/libs/orbitshield-0.0.1-SNAPSHOT.jar`.
 
### Executar JAR
```bash
java -jar build/libs/orbitshield-0.0.1-SNAPSHOT.jar
```

---

## Estrutura de Diretórios

```
orbitshield/
├── src/
│   ├── main/
│   │   ├── java/com/gs/orbitshield/
│   │   │   ├── controller/          ← REST Controllers
│   │   │   ├── service/             ← Business logic
│   │   │   ├── repository/          ← Data access
│   │   │   ├── model/               ← Entities
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   └── response/
│   │   │   ├── exception/           ← Custom exceptions
│   │   │   ├── filter/              ← API Key auth filter
│   │   │   ├── context/             ← Thread-local context
│   │   │   ├── util/                ← Utilities
│   │   │   ├── config/              ← Configuration beans
│   │   │   └── OrbitShieldApplication.java
│   │   └── resources/
│   │       ├── application.yaml     ← Configuração Spring
│   │       └── data.sql             ← Seed data
│   └── test/
│       └── java/com/gs/orbitshield/
│           └── OrbitShieldApplicationTests.java
├── build.gradle                     ← Dependências Gradle
├── README.md                        ← Este arquivo
└── .gitignore
```

---

## Contato & Suporte

Para dúvidas ou sugestões, contacte a equipe de desenvolvimento.

---

## Licença

Este projeto é parte da Global Solution FIAP 2026 e está protegido pelos termos da instituição.

