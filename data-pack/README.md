# Gói dữ liệu từ vựng chưa đưa vào app

`vocab-extra.json`: 42 nhóm / 122 mục đã soạn xong nhưng chưa nạp vào app (để dành cho bản cập nhật sau).
Cấu trúc mỗi nhóm: `gid, kind, adult, rel, tip, e[]`; mỗi mục có `w, pos, us, uk, vi, reg, nu (sắc thái), ex, exvi, oth (nghĩa khác), src, conf`.
Nguồn và giấy phép: xem `../NOTICE.md`. Trường `exsrc`/`src` cho biết ví dụ lấy từ WordNet hay tự biên soạn.
Ghi chú: các mục này chưa được người bản ngữ rà soát; các mục `adult: true` chỉ dành cho người từ 18 tuổi.

## vocab-pending.json (dữ liệu chờ nạp, tự biên soạn)

46 nhóm / 240 mục thuộc các mảng thông dụng: động từ chuyển động, giao tiếp, cảm xúc, tính từ kích thước/chất lượng, nấu ăn, công việc, tần suất, thời tiết, sức khỏe, mua sắm.
Mỗi nhóm có dạng `{ "batch", "adult", "g": [tip, [[word, pos, ipaUS, ipaUK, vi, use, ex, exVi, reg, say], ...]] }`.
`adult: true` thì nạp vào `D.search18`, ngược lại vào `D.search`. Khi nạp, quy tắc hiện có trong `www/index.html` tự thay các nhóm cũ có từ trùng.
IPA lấy từ CMU Pronouncing Dictionary; nghĩa, sắc thái, ví dụ do dự án tự biên soạn nên cần rà soát bởi người bản ngữ.
