# Gói dữ liệu từ vựng chưa đưa vào app

`vocab-extra.json`: 42 nhóm / 122 mục đã soạn xong nhưng chưa nạp vào app (để dành cho bản cập nhật sau).
Cấu trúc mỗi nhóm: `gid, kind, adult, rel, tip, e[]`; mỗi mục có `w, pos, us, uk, vi, reg, nu (sắc thái), ex, exvi, oth (nghĩa khác), src, conf`.
Nguồn và giấy phép: xem `../NOTICE.md`. Trường `exsrc`/`src` cho biết ví dụ lấy từ WordNet hay tự biên soạn.
Ghi chú: các mục này chưa được người bản ngữ rà soát; các mục `adult: true` chỉ dành cho người từ 18 tuổi.

## vocab-v1.4-merged.json (đã nạp vào app từ bản 1.4.0, giữ để tham khảo)

46 nhóm / 240 mục thuộc các mảng thông dụng: động từ chuyển động, giao tiếp, cảm xúc, tính từ kích thước/chất lượng, nấu ăn, công việc, tần suất, thời tiết, sức khỏe, mua sắm.
Mỗi nhóm có dạng `{ "batch", "adult", "g": [tip, [[word, pos, ipaUS, ipaUK, vi, use, ex, exVi, reg, say], ...]] }`.
`adult: true` thì nạp vào `D.search18`, ngược lại vào `D.search`. Khi nạp, quy tắc hiện có trong `www/index.html` tự thay các nhóm cũ có từ trùng.
IPA lấy từ CMU Pronouncing Dictionary; nghĩa, sắc thái, ví dụ do dự án tự biên soạn nên cần rà soát bởi người bản ngữ.

## phrasal-v1.5-merged.json (đã nạp vào app từ bản 1.5.0)

139 nhóm / 324 mục. Mỗi nhóm là MỘT động từ gốc cùng các giới từ đi kèm (ví dụ nhóm `bring`: bring up, bring in, bring out, bring back, bring about, bring down, bring forward, bring off). Phần `tip` của nhóm liệt kê nhanh nghĩa của từng giới từ.
Cụm có nhiều nghĩa (như pick up) được gộp thành một mục, các nghĩa phụ nằm trong trường `oth` (mục thứ 11 của mảng).
29 nhóm có `adult: true` (nạp vào mục 18+), phần còn lại là bản thường. Nghĩa 18+ của cùng một động từ (turn on, get off...) nằm ở nhóm 18+ riêng, không lẫn với bản thường.
Nhiều động từ mới chỉ có 1 đến 2 giới từ; có thể bổ sung thêm ở các lượt sau. Dự kiến thêm mục "Cụm động từ" (`phrasal`) và "Cụm động từ 18+" (`phrasal18`) trong `www/index.html`.
Nghĩa và ví dụ tự biên soạn, chưa có người bản ngữ rà soát; IPA lấy từ CMU. Đã bỏ các từ đơn không phải cụm động từ (click, bungle, bang, shag, dump) so với bản trước.
